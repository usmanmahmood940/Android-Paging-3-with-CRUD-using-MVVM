package com.example.paging_3_android.Retrofit

import com.example.paging_3_android.models.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page")page:Int):QuoteList
}