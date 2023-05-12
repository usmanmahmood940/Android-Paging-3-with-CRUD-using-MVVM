package com.example.paging_3_android.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.paging_3_android.models.SampleViewEvents
import com.example.paging_3_android.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import com.example.paging_3_android.models.Result
import kotlin.random.Random

@ExperimentalPagingApi
@HiltViewModel
class QuoteViewModel @Inject constructor(private val repository: QuoteRepository) : ViewModel() {
    private val modificationEvents = MutableStateFlow<List<SampleViewEvents>>(emptyList())
    val combined = repository.getQuotes().cachedIn(viewModelScope)
        .combine(modificationEvents) { pagingData, modifications ->
            modifications.fold(pagingData) { acc, event ->
                applyEvents(acc, event)
            }

        }

    val pagingDataViewStates: LiveData<PagingData<Result>> = combined.asLiveData()

    fun onViewEvent(sampleViewEvents: SampleViewEvents) {
        modificationEvents.value += sampleViewEvents
    }

    private fun applyEvents(
        paging: PagingData<Result>,
        sampleViewEvents: SampleViewEvents
    ): PagingData<Result> {
        return when (sampleViewEvents) {
            is SampleViewEvents.Remove -> {
                paging
                    .filter { sampleViewEvents.sampleEntity._id != it._id }
            }
            is SampleViewEvents.Edit -> {
                paging
                    .map {
                        if (sampleViewEvents.sampleEntity._id == it._id) return@map it.copy(content = "${sampleViewEvents.sampleEntity.content} (updated)")
                        else return@map it
                    }
            }
            SampleViewEvents.InsertItemHeader -> {
                paging.insertHeaderItem(
                    item = com.example.paging_3_android.models.Result(
                        _id = Random.nextInt(0, 1000).toString(),
                        content = "New item added at the top",
                        author = Random.toString(),
                        length = 15
                    )
                )
            }
            SampleViewEvents.InsertItemFooter -> {
                paging.insertFooterItem(
                    item =  com.example.paging_3_android.models.Result(
                        _id = Random.nextInt(0, 1000).toString(),
                        content = "New item added at the Bottom",
                        author = Random.toString(),
                        length = 15
                    )
                )
            }
        }
    }
}