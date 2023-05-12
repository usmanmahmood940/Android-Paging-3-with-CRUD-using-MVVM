package com.example.paging_3_android.models

import com.example.paging_3_android.models.Result
sealed class SampleViewEvents {
    data class Edit(val sampleEntity: Result) : SampleViewEvents()
    data class Remove(val sampleEntity: Result) : SampleViewEvents()
    object InsertItemHeader : SampleViewEvents()
    object InsertItemFooter : SampleViewEvents()
}