package com.parvesh.pixasearch.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.parvesh.pixasearch.cache.database.AppDatabase
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class TestCacheDao {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: CacheDao

    private lateinit var post1: PostEntity
    private lateinit var post2: PostEntity
    private lateinit var list: ArrayList<PostEntity>

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.cacheDao()

        post1 = PostEntity(
            "key1", 123, "thumbnail", "Bob",
            "Tag1, Tag2", "largeImage", 12, 23, 34, "test", 1
        )
        post2 = PostEntity(
            "key2", 123, "thumbnail", "Bob",
            "Tag1, Tag2", "largeImage", 12, 23, 34, "test", 1
        )
        list = ArrayList()
    }

    @After
    fun teardown() {
        list.clear()
        database.close()
    }

    @Test
    fun testSinglePostInsertion() = runBlockingTest {
        dao.insertPost(post1)
        dao.getPosts("test").test().assertValue { posts -> posts[0] == post1 }
    }

    @Test
    fun testMultiplePostsInsertionWithDifferentKeys() = runBlockingTest {
        list.add(post1)
        list.add(post2)
        dao.insertPosts(list)
        dao.getPosts("test").test()
            .assertValue { posts -> posts.size == 2 && posts[0] == post1 && posts[1] == post2 }
    }

    @Test
    fun testMultiplePostsInsertionWithSameKeys() = runBlockingTest {
        list.add(post1)
        list.add(post1)
        dao.insertPosts(list)
        dao.getPosts("test").test().assertValue { posts -> posts.size == 1 }
    }

    @Test
    fun testSearchEntityInsertion() = runBlockingTest {
        val searchEntity = SearchEntity("test", 100)
        dao.insertSearchEntity(searchEntity)
    }

}