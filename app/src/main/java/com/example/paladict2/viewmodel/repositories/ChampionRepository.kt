package com.example.paladict2.viewmodel.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.paladict2.Constants
import com.example.paladict2.model.Champion
import com.example.paladict2.model.ChampionDao
import com.example.paladict2.networking.PaladinsAPIService
import com.example.paladict2.utils.JavaUtils.createSignature
import com.example.paladict2.utils.JavaUtils.getDate
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import retrofit2.HttpException

class ChampionRepository(
    internal val championDao: ChampionDao
) {

    private var champions = mutableListOf<Champion>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        PaladinsAPIService.createCoreService()
    }

    private fun add(champions: List<Champion>) {
        championDao.insertAll(champions)
    }

    fun get(id: Int): LiveData<Champion> {
        return championDao.getChampionByID(id)
    }


    fun updateDBFromApi(session: String) {
        coroutineScope.launch {
            val request = thisApiCorService.getChampions(
                Constants.PALADINS_DEV_ID,
                createSignature("getchampions"),
                getDate(),
                session,
                "1"
            )
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    champions = response
                    addChampionsToDB(champions)
                } catch (e: HttpException) {
                    Log.d("", "")
                }
            }
        }
    }

    private fun addChampionsToDB(
        champions: MutableList<Champion>) {
        doAsync {
            add(champions)
        }
    }

}