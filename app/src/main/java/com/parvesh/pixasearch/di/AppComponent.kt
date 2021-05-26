package com.parvesh.pixasearch.di

import android.content.Context
import com.parvesh.pixasearch.ui.DetailsActivity
import com.parvesh.pixasearch.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        CacheModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun injectMainActivity(activity: MainActivity)
    fun injectDetailsActivity(activity: DetailsActivity)
}
