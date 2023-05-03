package com.example.paging_3_android.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging_3_android.models.QuoteRemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("Select * From QuoteRemoteKeys Where id = :id")
    suspend fun getRemoteKeys(id:String):QuoteRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<QuoteRemoteKeys>)

    @Query("Delete From QuoteRemoteKeys")
    suspend fun deleteAllRemoteKeys()

}