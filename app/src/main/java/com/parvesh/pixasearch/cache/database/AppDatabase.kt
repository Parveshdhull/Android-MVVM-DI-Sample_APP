package com.parvesh.pixasearch.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parvesh.pixasearch.cache.CacheDao
import com.parvesh.pixasearch.cache.models.PostEntity
import com.parvesh.pixasearch.cache.models.SearchEntity

@Database(entities = [PostEntity::class, SearchEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cacheDao(): CacheDao

    companion object {
        const val DATABASE_NAME: String = "pixa_search_db"
    }

}