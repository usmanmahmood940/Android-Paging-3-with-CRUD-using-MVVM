package com.example.paging_3_android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.paging_3_android.db.QuoteDao
import com.example.paging_3_android.db.RemoteKeysDao
import com.example.paging_3_android.models.QuoteRemoteKeys
import com.example.paging_3_android.models.Result

@Database(entities = [Result::class,QuoteRemoteKeys::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun quoteDao():QuoteDao

    abstract fun remoteKeysDao():RemoteKeysDao

}