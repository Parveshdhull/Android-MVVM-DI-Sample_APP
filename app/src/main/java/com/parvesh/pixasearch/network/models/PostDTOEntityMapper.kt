package com.parvesh.pixasearch.network.models

import com.parvesh.pixasearch.cache.models.PostEntity
import javax.inject.Inject

class PostDTOEntityMapper @Inject constructor(){
    fun mapToPostEntity(postDto: PostDTO, searchTerm: String): PostEntity {
        return PostEntity(
            postDto.id,
            postDto.thumbnail,
            postDto.userName,
            postDto.tags,
            postDto.largeImage,
            postDto.likesCount,
            postDto.favoritesCount,
            postDto.commentsCount,
            searchTerm
        )
    }

    fun mapFromPostEntity(postEntity: PostEntity): PostDTO {
        return PostDTO(
            postEntity.id,
            postEntity.thumbnail,
            postEntity.userName,
            postEntity.tags,
            postEntity.largeImage,
            postEntity.likesCount,
            postEntity.favoritesCount,
            postEntity.commentsCount
        )
    }

    fun toPostEntitiesList(initial: List<PostDTO>, searchTerm: String): List<PostEntity>{
        return initial.map { mapToPostEntity(it, searchTerm) }
    }

    fun fromPostEntitiesList(initial: List<PostEntity>): List<PostDTO>{
        return initial.map { mapFromPostEntity(it) }
    }
}