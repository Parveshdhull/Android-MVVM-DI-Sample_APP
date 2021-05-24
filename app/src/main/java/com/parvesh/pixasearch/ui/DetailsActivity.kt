package com.parvesh.pixasearch.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.RequestManager
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.databinding.ActivityDetailsBinding
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {


    // Inject Glide Request Manager
    @Inject
    lateinit var requestManager: RequestManager

    lateinit var userName: String
    lateinit var tags: String
    lateinit var likesCount: String
    lateinit var favoritesCount: String
    lateinit var commentsCount: String
    private lateinit var largeImage: String
    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as PixaApplication).appComponent.injectDetailsActivity(this)

        super.onCreate(savedInstanceState)
        val binding: ActivityDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.data = this
        binding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        if (extras != null) {
            userName = "User: " + extras.getString("userName")
            tags = "Tags: " + extras.getString("tags")
            likesCount = "Likes: " + extras.getInt("likesCount")
            favoritesCount = "Favourites: " + extras.getInt("favoritesCount")
            commentsCount = "Comments: " + extras.getInt("commentsCount")
            largeImage = extras.getString("largeImage")?:""
        }

        imageView = findViewById(R.id.activity_details_image_view)

        requestManager.load(largeImage).into(imageView)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}