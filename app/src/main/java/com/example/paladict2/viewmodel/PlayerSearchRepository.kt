package com.example.paladict2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.utils.JavaUtils.createSignature
import com.example.paladict2.utils.JavaUtils.getDate
import com.example.paladict2.model.Player
import com.example.paladict2.networking.PaladinsAPIService
import kotlinx.coroutines.*
import retrofit2.HttpException

class PlayerSearchRepository {
    private var playerSearchList = mutableListOf<Player>()
    private var mutableSearchList = MutableLiveData<List<Player>>()

    internal val completableJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCoreService by lazy {
        PaladinsAPIService.createCoreService()
    }

    fun getMutableLiveData(session: String, playerID: String) : MutableLiveData<List<Player>> {
        coroutineScope.launch {
            val request = thisApiCoreService.searchPlayers(
                Constants.PALADINS_DEV_ID,
                createSignature("searchplayers"),
                getDate(),
                session,
                playerID
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    playerSearchList = response
                    mutableSearchList.value = playerSearchList
                } catch (e: HttpException){
                    Log.d("HTTP", "HTTP ERROR!")
                }
            }
        }
        return mutableSearchList
    }

}