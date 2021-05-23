package com.parvesh.pixasearch.di

import com.google.gson.GsonBuilder
import com.parvesh.pixasearch.network.RetrofitSearchService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideSearchService(): RetrofitSearchService {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitSearchService::class.java)
    }

    @Singleton
    @Provides
    @Named("api_key")
    fun provideApiKey(): String{
        return "21736348-b069a7496d70ba46695b66779"
    }

}