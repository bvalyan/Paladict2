package com.example.paladict2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.PaladinsAPIService
import kotlinx.coroutines.*
import java.lang.Exception

class ChampionRepository {

    private var champions = mutableListOf<Champion>()
    private var mutableLiveData = MutableLiveData<List<Champion>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCorService()
    }

    fun getMutableLiveData(date: String, signature: String, sessionID: String): MutableLiveData<List<Champion>> {
        coroutineScope.launch {
            val request = thisApiCorService.getChampions(Constants.PALADINS_DEV_ID, signature, date, sessionID, "1")
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if(response != null){
                        champions = response as MutableList<Champion>
                    }
                } catch ( e : Exception){
                    Log.d("", "")
                }
            }
        }
        return mutableLiveData
    }
}