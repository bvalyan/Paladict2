package com.example.paladict2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.Utils
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.PaladinsAPIService
import kotlinx.coroutines.*
import retrofit2.HttpException

class ChampionRepository {

    private var champions = mutableListOf<Champion>()
    private var mutableLiveData = MutableLiveData<List<Champion>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCorService()
    }

    fun getMutableLiveData(session : String) : MutableLiveData<List<Champion>> {
        coroutineScope.launch {
            val request = thisApiCorService.getChampions(
                Constants.PALADINS_DEV_ID,
                Utils.createSignature("getchampions"),
                Utils.getDate(),
                session,
                "1"
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    val mChampions = response
                    champions = mChampions
                    mutableLiveData.value = champions
                } catch (e: HttpException){
                    Log.d("", "")
                }
            }
        }
        return mutableLiveData
    }

}