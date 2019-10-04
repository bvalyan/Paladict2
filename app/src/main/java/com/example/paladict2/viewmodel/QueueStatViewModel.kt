package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.paladict2.model.MergedQueueSearchData
import com.example.paladict2.viewmodel.repositories.QueueStatRepository

class QueueStatViewModel : ViewModel() {
    var queueStatRepository = QueueStatRepository()
    val combinedQueueSearchData  = MergedQueueSearchData()
}