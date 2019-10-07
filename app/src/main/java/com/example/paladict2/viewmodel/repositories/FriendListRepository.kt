package com.example.paladict2.viewmodel.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.model.Match
import com.example.paladict2.model.Player
import com.example.paladict2.networking.PaladinsAPIService
import com.example.paladict2.utils.JavaUtils
import kotlinx.coroutines.*
import retrofit2.HttpException

class FriendListRepository {

    private var players = mutableListOf<Player>()
    private var mutableLiveData = MutableLiveData<List<Player>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCoreService by lazy {
        PaladinsAPIService.createCoreService()
    }

    fun getFriendMutableLiveData(session : String, playerID : String) : MutableLiveData<List<Player>> {
        coroutineScope.launch {
            val request = thisApiCoreService.getFriends(
                Constants.PALADINS_DEV_ID,
                JavaUtils.createSignature("getfriends"),
                JavaUtils.getDate(),
                session,
                playerID
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    players = response
                    mutableLiveData.value = players
                } catch (e: HttpException){
                    Log.d("", "")
                }
            }
        }
        return mutableLiveData
    }


}
