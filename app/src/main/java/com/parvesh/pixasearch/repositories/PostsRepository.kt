package com.parvesh.pixasearch.repositories

import android.content.Context
import com.parvesh.pixasearch.R
import com.parvesh.pixasearch.cache.CacheDao
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import com.parvesh.pixasearch.network.RetrofitSearchService
import com.parvesh.pixasearch.network.models.PostDTOEntityMapper
import com.parvesh.pixasearch.utils.Utils
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val searchService: RetrofitSearchService,
    private val postDTOEntityMapper: PostDTOEntityMapper,
    private val dao: CacheDao,
    private val context: Context
) : PostsRepositoryInterface {

    override suspend fun getPosts(
        searchTerm: String
    ): Flowable<List<PostEntity>> {
        return dao.getPosts(searchTerm)
    }

    override suspend fun getSearchEntity(searchTerm: String): Flowable<SearchEntity> {
        return dao.getSearchEntity(searchTerm)
    }

    override suspend fun updateCache(searchTerm: String, page: Int) {
        try {
            val response = searchService.searchPosts(
                key = context.getString(R.string.api_key),
                query = searchTerm,
                image_type = context.getString(R.string.image_type),
                page = page
            )
            val postEntitiesList =
                postDTOEntityMapper.toPostEntitiesList(response.hits, searchTerm, page - 1)
            dao.insertPosts(postEntitiesList)
            dao.insertSearchEntity(SearchEntity(searchTerm, response.totalHits))
        } catch (e: Exception) {
            Utils.log("Repository Exception", "" + e.message)
            dao.insertSearchEntity(SearchEntity(searchTerm, 0))
        }
    }

}