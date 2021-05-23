package com.parvesh.pixasearch.repository

import com.parvesh.pixasearch.cache.PostDao
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import com.parvesh.pixasearch.network.RetrofitSearchService
import com.parvesh.pixasearch.network.models.PostDTOEntityMapper
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class PostsRepository @Inject constructor(
        private val searchService: RetrofitSearchService,
        private val postDTOEntityMapper: PostDTOEntityMapper,
        private val dao: PostDao
): PostsRepositoryInterface {

    override suspend fun getPosts(key: String, searchTerm: String, image_type: String, page: Int, limit: Int): Flowable<List<PostEntity>> {
        updateCache(key, searchTerm, image_type, page)
        return dao.getPosts(searchTerm, limit)
    }

    override suspend fun getSearchEntity(searchTerm: String): Flowable<SearchEntity> {
        return dao.getSearchEntity(searchTerm)
    }

    private suspend fun updateCache(key: String, searchTerm: String, image_type: String, page: Int){
        val response = searchService.searchPosts(
            key = key,
            query = searchTerm,
            image_type = image_type,
            page = page
        )

        val postEntitiesList = postDTOEntityMapper.toPostEntitiesList(response.hits, searchTerm)

        dao.insertSearchEntity(SearchEntity(searchTerm, response.totalHits))
        dao.insertPosts(postEntitiesList)
    }
}