package com.parvesh.pixasearch.repository

import com.parvesh.pixasearch.domain.models.Post
import com.parvesh.pixasearch.network.RetrofitSearchService
import com.parvesh.pixasearch.network.models.PostDTOMapper

class PostsRepository (
        private val searchService: RetrofitSearchService,
        private val mapper: PostDTOMapper,
): PostsRepositoryInterface {

    override suspend fun search(key: String, query: String, image_type: String, page: Int): List<Post> {
        return mapper.toDomainList(searchService.searchPosts(key = key, query = query, image_type = image_type, page = page).hits)
    }

}