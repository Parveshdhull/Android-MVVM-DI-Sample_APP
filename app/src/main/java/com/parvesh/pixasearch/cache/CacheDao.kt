package com.parvesh.pixasearch.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CacheDao {

    // Post Related Code
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts WHERE searchTerm = :searchTerm ORDER BY postsOrder")
    fun getPosts(searchTerm: String): Flowable<List<PostEntity>>

    // Search Related Code
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchEntity(searchEntity: SearchEntity)

    @Query("SELECT * FROM searchTerms WHERE searchTerm = :searchTerm")
    fun getSearchEntity(searchTerm: String): Flowable<SearchEntity>

}