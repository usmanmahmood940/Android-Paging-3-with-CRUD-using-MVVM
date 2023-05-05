package com.example.paging_3_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.filter
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging_3_android.Paging.LoaderAdapter
import com.example.paging_3_android.Paging.QuotePagingAdapter
import com.example.paging_3_android.databinding.ActivityMainBinding
import com.example.paging_3_android.models.Result
import com.example.paging_3_android.models.SampleViewEvents
import com.example.paging_3_android.viewModels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import java.util.Timer

@ExperimentalPagingApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    lateinit var quoteViewModel: QuoteViewModel
    lateinit var adapter:QuotePagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)



        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)

        adapter = QuotePagingAdapter(quoteViewModel)

        mainBinding.rvQuoteList.layoutManager = LinearLayoutManager(this)
        mainBinding.rvQuoteList.setHasFixedSize(true)
        mainBinding.rvQuoteList.adapter = adapter

        mainBinding.rvQuoteList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

//

        mainBinding.btnInsertTop.setOnClickListener {
            quoteViewModel.onViewEvent(SampleViewEvents.InsertItemHeader)
        }

        // For liveData
        quoteViewModel.pagingDataViewStates.observe(this){
            adapter.submitData(lifecycle,it)
//            val list = adapter.snapshot().items
      
        }
//        quoteViewModel.list.observe(this){
//            adapter.submitData(lifecycle,it)
//        }

    }
}