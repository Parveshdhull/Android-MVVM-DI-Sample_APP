package com.parvesh.pixasearch.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Singleton
    @Provides
    fun randomString(): String{
        return "Just a dummy String"
    }

}