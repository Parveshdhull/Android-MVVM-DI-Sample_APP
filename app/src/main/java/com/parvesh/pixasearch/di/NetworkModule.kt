package com.parvesh.pixasearch.di

import com.parvesh.pixasearch.network.models.PostDTOMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun randomString(): String{
        return "You Rock Man!!"
    }

    @Singleton
    @Provides
    fun providePostMapper(): PostDTOMapper {
        return PostDTOMapper()
    }

}