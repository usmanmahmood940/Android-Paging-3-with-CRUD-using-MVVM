package com.example.paging_3_android.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging_3_android.models.Result

@Dao
interface QuoteDao {

    @Query("Select * From Quote")
    fun getQuotes():PagingSource<Int,Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(quotes:List<Result>)

    @Query("DELETE From Quote")
    suspend fun deleteQuote()
    
}