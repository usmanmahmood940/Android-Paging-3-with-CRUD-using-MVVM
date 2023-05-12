package com.example.paging_3_android.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quote")
data class Result(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val author: String,
    var content: String,
    val length: Int,
)