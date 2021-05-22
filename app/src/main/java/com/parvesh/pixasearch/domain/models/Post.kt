package com.parvesh.pixasearch.domain.models

data class Post (
    val thumbnail:String,
    val userName:String,
    val tags:String,
    val largeImage:String,
    val likesCount:Int,
    val favoritesCount:Int,
    val commentsCount:Int
    )