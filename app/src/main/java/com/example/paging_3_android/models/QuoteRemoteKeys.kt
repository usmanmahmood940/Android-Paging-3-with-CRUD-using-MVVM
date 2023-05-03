package com.example.paging_3_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "QuoteRemoteKeys")
data class QuoteRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage:Int?,
    val nextPage:Int?
)