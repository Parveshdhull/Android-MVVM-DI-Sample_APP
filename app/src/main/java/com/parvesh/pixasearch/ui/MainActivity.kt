package com.parvesh.pixasearch.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.databinding.ActivityMainBinding
import com.parvesh.pixasearch.repository.PostsRepository
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: MainActivityViewModel by viewModels { factory }

    private lateinit var searchView: SearchView


    @Inject
    lateinit var postsRepository: PostsRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as PixaApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.test()

        searchView = findViewById(R.id.main_activity_search_view)

        searchView.setQuery("apple", true)

        binding.viewModel = viewModel
        

    }
}