package com.parvesh.pixasearch

import android.app.Application
import com.parvesh.pixasearch.di.AppComponent
import com.parvesh.pixasearch.di.DaggerAppComponent

open class PixaApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}