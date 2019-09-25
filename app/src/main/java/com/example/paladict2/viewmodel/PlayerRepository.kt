package com.example.paladict2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.Utils
import com.example.paladict2.model.Player
import com.example.paladict2.networking.PaladinsAPIService
import kotlinx.coroutines.*
import retrofit2.HttpException

class PlayerRepository {
    private var player = Player()
    private var mutableLiveData = MutableLiveData<Player>()

    internal val completableJob = Job()

    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCoreService by lazy {
        PaladinsAPIService.createCoreService()
    }

    fun getMutableLiveData(session : String, portalID : String, playerID : String) : MutableLiveData<Player> {
        coroutineScope.launch {
            val request = thisApiCoreService.getplayer(
                Constants.PALADINS_DEV_ID,
                Utils.createSignature("getplayer"),
                Utils.getDate(),
                session,
                playerID,
                portalID
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    player = response
                    mutableLiveData.value = player
                } catch (e: HttpException){
                Log.d("HTTP", "HTTP ERROR!")
                }
            }
        }
        return mutableLiveData
    }

}