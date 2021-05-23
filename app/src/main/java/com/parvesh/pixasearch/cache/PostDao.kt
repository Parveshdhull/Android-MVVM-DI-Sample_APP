package com.parvesh.pixasearch.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PostDao {

    // Post Related Code
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM posts WHERE searchTerm = :searchTerm LIMIT :limit")
    suspend fun getPosts(searchTerm: String, limit: Int): Flowable<List<PostEntity>>

    // Search Related Code
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSearchEntity(searchEntity: SearchEntity)

    @Query("SELECT * FROM searchTerms WHERE searchTerm = :searchTerm")
    suspend fun getSearchEntity(searchTerm: String): Flowable<SearchEntity>

}