package com.parvesh.pixasearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.Utils.Util
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var abc: String

    override fun onCreate(savedInstanceState: Bundle?) {

        (applicationContext as PixaApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Util.log("Main Activity String", abc)
    }
}