package com.example.paging_3_android.repository

import androidx.paging.*
import com.example.paging_3_android.Paging.QuotePagingSource
import com.example.paging_3_android.Paging.QuoteRemoteMediator
import com.example.paging_3_android.QuoteDatabase
import com.example.paging_3_android.Retrofit.QuoteAPI
import com.example.paging_3_android.models.Result
import java.util.concurrent.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class QuoteRepository @Inject constructor(
    private val quoteAPI: QuoteAPI,
    private val quoteDatabase: QuoteDatabase
) {


    fun getQuotes() = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
        ),
        remoteMediator = QuoteRemoteMediator(quoteAPI,quoteDatabase),
        pagingSourceFactory = {quoteDatabase.quoteDao().getQuotes()}
    ).flow
}