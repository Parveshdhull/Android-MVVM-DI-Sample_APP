package com.parvesh.pixasearch.cache.models

import com.parvesh.pixasearch.domain.models.Post
import javax.inject.Inject

class PostDomainEntityMapper @Inject constructor() {
    fun mapToDomainModel(model: PostEntity): Post {
        return Post(
            model.key,
            model.id,
            model.thumbnail,
            model.userName,
            model.tags,
            model.largeImage,
            model.likesCount,
            model.favoritesCount,
            model.commentsCount,
            model.postsOrder
        )
    }

    fun mapFromDomainModel(domainModel: Post, searchTerm: String): PostEntity {
        return PostEntity(
            domainModel.key,
            domainModel.id,
            domainModel.thumbnail,
            domainModel.userName,
            domainModel.tags,
            domainModel.largeImage,
            domainModel.likesCount,
            domainModel.favoritesCount,
            domainModel.commentsCount,
            searchTerm,
            domainModel.postsOrder
        )
    }

    fun toDomainList(initial: List<PostEntity>): List<Post> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Post>, searchTerm: String): List<PostEntity> {
        return initial.map { mapFromDomainModel(it, searchTerm) }
    }
}