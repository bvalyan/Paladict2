package com.example.paladict2.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.PaladinsAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ChampionRepository {

    private var champions = mutableListOf<Champion>()
    private var mutableLiveData = MutableLiveData<List<Champion>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCorService()
    }

}