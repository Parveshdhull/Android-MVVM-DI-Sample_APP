package com.parvesh.pixasearch.di


import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.domain.models.Post
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun provideGlideOptions(): RequestOptions {
        var requestOptions = RequestOptions()
        requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        requestOptions.placeholder(R.drawable.ic_logo_square)
        requestOptions.error(R.drawable.ic_logo_square)
        return requestOptions
    }

    @Singleton
    @Provides
    fun provideGlideInstance(context: Context, requestOptions: RequestOptions): RequestManager {
        return Glide.with(context)
            .setDefaultRequestOptions(requestOptions)
    }

    @Singleton
    @Provides
    fun provideEmptyDataList(): ArrayList<Post> {
        return ArrayList()
    }

}