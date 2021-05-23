package com.parvesh.pixasearch.network.models

import com.google.gson.annotations.SerializedName

data class PostDTO (

    @SerializedName("id")
    val id:Int,
    
    @SerializedName("previewURL")
    val thumbnail:String,

    @SerializedName("user")
    val userName:String,

    @SerializedName("tags")
    val tags:String,

    @SerializedName("largeImageURL")
    val largeImage:String,

    @SerializedName("likes")
    val likesCount:Int,

    @SerializedName("favorites")
    val favoritesCount:Int,

    @SerializedName("comments")
    val commentsCount:Int

    )