package com.example.paladict2.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.Constants
import com.example.paladict2.Utils.createSignature
import com.example.paladict2.Utils.getDate
import com.example.paladict2.model.Session
import com.example.paladict2.networking.PaladinsAPIService
import kotlinx.coroutines.*
import retrofit2.HttpException


class SessionRepository {

    private var session = Session()
    private var mutableLiveData = MutableLiveData<Session>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCorService()
    }

    fun getMutableLiveData() : MutableLiveData<Session> {
        coroutineScope.launch {
            val request = thisApiCorService.createAPISession(Constants.PALADINS_DEV_ID, createSignature("createsession"), getDate())
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    val mSession = response
                    session = mSession
                    mutableLiveData.value = session
                } catch (e: HttpException){
                    Log.d("", "")
                }
            }
        }
       return mutableLiveData
    }
}