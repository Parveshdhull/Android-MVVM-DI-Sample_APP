package com.parvesh.pixasearch.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.parvesh.pixasearch.cache.PostDao
import com.parvesh.pixasearch.cache.models.PostEntity

@Database(entities = [PostEntity::class ], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object{
        val DATABASE_NAME: String = "pixa_search_db"
    }


}