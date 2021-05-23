package com.parvesh.pixasearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.repository.PostsRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    val viewModel: MainActivityViewModel by viewModels { factory }


    @Inject
    lateinit var postsRepository: PostsRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as PixaApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.test()


    }
}