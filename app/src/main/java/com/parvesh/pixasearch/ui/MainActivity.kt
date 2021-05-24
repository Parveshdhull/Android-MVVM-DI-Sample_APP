package com.parvesh.pixasearch.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.adapters.MainActivityRecyclerViewAdapter
import com.parvesh.pixasearch.databinding.ActivityMainBinding
import com.parvesh.pixasearch.domain.models.Post
import com.parvesh.pixasearch.ui.viewmodels.MainActivityViewModel
import com.parvesh.pixasearch.utils.RecyclerTouchListener
import com.parvesh.pixasearch.utils.Utils
import javax.inject.Inject


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    // ViewModel
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: MainActivityViewModel by viewModels { factory }

    // Insert Recycler View Adapter
    @Inject
    lateinit var adapter: MainActivityRecyclerViewAdapter

    // Views
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView

    // Recycler View adapter and dataset
    private var dataset: ArrayList<Post> = ArrayList()

    private var downloading: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as PixaApplication).appComponent.injectMainActivity(this)

        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        searchView = findViewById(R.id.main_activity_search_view)
        recyclerView = findViewById(R.id.main_activity_recyler_view)

        recylerViewInitialize()

        registerObservers()

        searchView.setOnQueryTextListener(this)
        searchView.setQuery("fruits", true)
    }

    private fun recylerViewInitialize(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Download new data when recycler view reaches bottom
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE && !downloading) {
                    downloading = true
                    viewModel.downloadMorePosts()
                }
            }
        })

        // Touch Listener
        recyclerView.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                recyclerView,
                object : RecyclerTouchListener.OnItemClickListener {
                    override fun onClick(view: View?, position: Int) {
                        if(position < dataset.size){
                            showDetails(dataset[position])
                        }
                    }
                    override fun onLongClick(view: View?, position: Int) {
                    }
                })
        )
    }

    private fun registerObservers(){
        viewModel.getPosts().observe(this, {posts ->
            run {
                downloading = false
                dataset = posts as ArrayList<Post>
                adapter.update(dataset)
            }
        })

        viewModel.errorToast.observe(this, {error ->
            run{
                if(error?.isNotEmpty() == true){
                    downloading = false
                    Utils.showShortToast(this, error)
                    viewModel.errorToast.postValue("")
                }
            }
        })
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true  // Do Nothing
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if(!downloading) {
            downloading = true
            viewModel.search(p0 + "")
        }
        return true
    }

    private fun showDetails(post:Post){
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage("See more Details?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id -> openDetailsActivity(post)}
            .setNegativeButton("No", null)
        val alert = builder.create()
        alert.show()
    }

    private fun openDetailsActivity(post: Post){
        var intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("largeImage", post.largeImage)
        intent.putExtra("userName", post.userName)
        intent.putExtra("tags", post.tags)
        intent.putExtra("likesCount", post.likesCount)
        intent.putExtra("favoritesCount", post.favoritesCount)
        intent.putExtra("commentsCount", post.commentsCount)
        startActivity(intent)
    }
}