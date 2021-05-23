package com.parvesh.pixasearch.di

import android.content.Context
import androidx.room.Room
import com.parvesh.pixasearch.cache.CacheDao
import com.parvesh.pixasearch.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDao(db: AppDatabase): CacheDao{
        return db.postDao()
    }

}