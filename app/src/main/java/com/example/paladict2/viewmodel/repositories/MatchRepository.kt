package com.example.paladict2.viewmodel.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.model.Match
import com.example.paladict2.networking.PaladinsAPIService
import com.example.paladict2.utils.JavaUtils
import kotlinx.coroutines.*
import retrofit2.HttpException

class MatchRepository {

    private var matches = mutableListOf<Match>()
    private var mutableLiveData = MutableLiveData<List<Match>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCoreService()
    }

    fun getMatchMutableLiveData(session : String, playerID : String) : MutableLiveData<List<Match>> {
        coroutineScope.launch {
            val request = thisApiCorService.getMatchHistory(
                Constants.PALADINS_DEV_ID,
                JavaUtils.createSignature("getmatchhistory"),
                JavaUtils.getDate(),
                session,
                playerID
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    matches = response
                    mutableLiveData.value = matches
                } catch (e: HttpException){
                    Log.d("", "")
                }
            }
        }
        return mutableLiveData
    }

}
