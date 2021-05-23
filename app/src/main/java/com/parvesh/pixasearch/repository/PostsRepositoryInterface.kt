package com.parvesh.pixasearch.repository

import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import io.reactivex.rxjava3.core.Flowable

interface PostsRepositoryInterface {

    suspend fun getPosts(key: String, searchTerm: String, image_type: String, page: Int, limit: Int): Flowable<List<PostEntity>>

    suspend fun getSearchEntity(searchTerm: String): Flowable<SearchEntity>

}