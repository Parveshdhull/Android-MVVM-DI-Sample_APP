package com.parvesh.pixasearch.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searchTerms")
data class SearchEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "searchTerm")
    val searchTerm: String,

    @ColumnInfo(name = "totalHits")
    val totalHits: Int
)