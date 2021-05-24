package com.parvesh.pixasearch.repository

import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import io.reactivex.rxjava3.core.Flowable

interface PostsRepositoryInterface {

    suspend fun getPosts(searchTerm: String): Flowable<List<PostEntity>>

    suspend fun getSearchEntity(searchTerm: String): Flowable<SearchEntity>

    suspend fun updateCache(searchTerm: String, page: Int)
}