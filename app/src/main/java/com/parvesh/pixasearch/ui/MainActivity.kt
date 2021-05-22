package com.parvesh.pixasearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.Utils.Util
import com.parvesh.pixasearch.repository.PostsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var postsRepository: PostsRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as PixaApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            Util.log(
                "Main Activity String", "" +postsRepository.search(
                    "21736348-b069a7496d70ba46695b66779",
                    "apple+mango",
                    "photo",
                    1
                )
            )
        }
    }
}