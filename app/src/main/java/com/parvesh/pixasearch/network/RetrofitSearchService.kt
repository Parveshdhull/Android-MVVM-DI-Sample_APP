package com.parvesh.pixasearch.network

import com.parvesh.pixasearch.network.response.RetrofitSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitSearchService {

    @GET("api")
    suspend fun searchPosts(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("image_type") image_type: String = "photo",
        @Query("page") page: Int
    ): RetrofitSearchResponse

}
