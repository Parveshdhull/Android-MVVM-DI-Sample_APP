package com.parvesh.pixasearch.di

import androidx.room.Room
import com.parvesh.pixasearch.PixaApplication
import com.parvesh.pixasearch.cache.PostDao
import com.parvesh.pixasearch.cache.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: PixaApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostsDao(db: AppDatabase): PostDao{
        return db.postDao()
    }

}