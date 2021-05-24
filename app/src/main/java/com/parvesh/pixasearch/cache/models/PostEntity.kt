package com.parvesh.pixasearch.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "previewURL")
    val thumbnail:String,

    @ColumnInfo(name = "user")
    val userName:String,

    @ColumnInfo(name = "tags")
    val tags:String,

    @ColumnInfo(name = "largeImageURL")
    val largeImage:String,

    @ColumnInfo(name = "likes")
    val likesCount:Int,

    @ColumnInfo(name = "favorites")
    val favoritesCount:Int,

    @ColumnInfo(name = "comments")
    val commentsCount:Int,

    @ColumnInfo(name = "searchTerm")
    val searchTerm:String,

    @ColumnInfo(name = "postsOrder")
    val postsOrder: Int
)