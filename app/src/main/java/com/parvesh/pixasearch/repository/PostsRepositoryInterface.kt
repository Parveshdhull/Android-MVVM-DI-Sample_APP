package com.parvesh.pixasearch.repository

import com.parvesh.pixasearch.domain.models.Post

interface PostsRepositoryInterface {

    suspend fun search(key: String, query: String, image_type: String, page: Int): List<Post>

}