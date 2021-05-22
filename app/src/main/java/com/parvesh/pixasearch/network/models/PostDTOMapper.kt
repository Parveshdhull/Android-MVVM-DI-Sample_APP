package com.parvesh.pixasearch.network.models

import com.parvesh.pixasearch.domain.models.Post
import com.parvesh.pixasearch.domain.util.DomainMapper

class PostDTOMapper : DomainMapper<PostDTO, Post>{
    override fun mapToDomainModel(model: PostDTO): Post {
        return Post(
            model.thumbnail,
            model.userName,
            model.tags,
            model.largeImage,
            model.likesCount,
            model.favoritesCount,
            model.commentsCount
        )
    }

    override fun mapFromDomainModel(domainModel: Post): PostDTO {
        return PostDTO(
            domainModel.thumbnail,
            domainModel.userName,
            domainModel.tags,
            domainModel.largeImage,
            domainModel.likesCount,
            domainModel.favoritesCount,
            domainModel.commentsCount
        )
    }

    fun toDomainList(initial: List<PostDTO>): List<Post>{
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Post>): List<PostDTO>{
        return initial.map { mapFromDomainModel(it) }
    }
}