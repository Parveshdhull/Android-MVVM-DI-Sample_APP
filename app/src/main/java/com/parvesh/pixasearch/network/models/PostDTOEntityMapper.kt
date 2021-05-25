package com.parvesh.pixasearch.network.models

import com.parvesh.pixasearch.cache.models.PostEntity
import javax.inject.Inject

class PostDTOEntityMapper @Inject constructor() {
    fun mapToPostEntity(postDto: PostDTO, searchTerm: String, postOrder: Int): PostEntity {
        return PostEntity(
            searchTerm + postDto.id,
            postDto.id,
            postDto.thumbnail,
            postDto.userName,
            postDto.tags,
            postDto.largeImage,
            postDto.likesCount,
            postDto.favoritesCount,
            postDto.commentsCount,
            searchTerm,
            postOrder
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

    fun toPostEntitiesList(
        initial: List<PostDTO>,
        searchTerm: String,
        multiplier: Int
    ): List<PostEntity> {
        return initial.mapIndexed { index, postDto ->
            mapToPostEntity(
                postDto,
                searchTerm,
                (multiplier * 20) + (index + 1)
            )
        }
    }

    fun fromPostEntitiesList(initial: List<PostEntity>): List<PostDTO> {
        return initial.map { mapFromPostEntity(it) }
    }
}