package com.example.paging_3_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging_3_android.Paging.LoaderAdapter
import com.example.paging_3_android.Paging.QuotePagingAdapter
import com.example.paging_3_android.databinding.ActivityMainBinding
import com.example.paging_3_android.viewModels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

        adapter = QuotePagingAdapter()

        mainBinding.rvQuoteList.layoutManager = LinearLayoutManager(this)
        mainBinding.rvQuoteList.setHasFixedSize(true)
        mainBinding.rvQuoteList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

//        // For flow
//        lifecycleScope.launch {
//            quoteViewModel.list.collectLatest { response->
//                Log.d("main", "onCreate: $response")
//                adapter.submitData(response)
//            }
//
//        }

        // For liveData
        quoteViewModel.list.observe(this){
            adapter.submitData(lifecycle,it)
        }

    }
}