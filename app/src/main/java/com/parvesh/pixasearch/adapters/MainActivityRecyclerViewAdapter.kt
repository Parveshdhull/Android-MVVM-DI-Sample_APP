package com.parvesh.pixasearch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.domain.models.Post
import javax.inject.Inject

class MainActivityRecyclerViewAdapter @Inject constructor(private val context: Context, private var dataset: ArrayList<Post>, private val requestManager: RequestManager) :
        RecyclerView.Adapter<MainActivityRecyclerViewAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnailView: ImageView = view.findViewById(R.id.item_activity_main_thumbnail)
        val userView: TextView = view.findViewById(R.id.item_activity_main_user)
        val tagsView: TextView = view.findViewById(R.id.item_activity_main_tags)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_activity_main, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(position <= dataset.size) {
            val post: Post = dataset.get(position)
            requestManager.load(post.thumbnail).into(viewHolder.thumbnailView)
            (context.getString(R.string.user_name_prefix) + " " + post.userName).also { viewHolder.userView.text = it }
            (context.getString(R.string.tags_prefix) + " " + post.tags).also { viewHolder.tagsView.text = it }
        }
    }

    override fun getItemCount() = dataset.size

    fun update(dataSet: ArrayList<Post>){
        this.dataset = dataSet
        notifyDataSetChanged()
    }

}
