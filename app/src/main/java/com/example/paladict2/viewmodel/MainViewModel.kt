package com.example.paladict2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.paladict2.model.Champion
import com.example.paladict2.model.PaladictDatabase
import com.example.paladict2.viewmodel.repositories.ChampionRepository

class MainViewModel(var session: String, application: Application) : AndroidViewModel(application) {

    private val championRepository: ChampionRepository
    private val champions = MutableLiveData<Pair<List<Champion>?, Error?>>()
    val mChampionsLive = MediatorLiveData<List<Champion>>()


    init {
        val championDao = PaladictDatabase.getInstance(application).championDao()
        championRepository = ChampionRepository(championDao)
        getAllChampions()
    }



    private fun updateDBFromApi() {
        championRepository.updateDBFromApi(session)
    }

    private fun getAllChampions() : LiveData<List<Champion>> {
        val champions = championRepository.championDao.getAllChampions()
        mChampionsLive.addSource(champions) {
            if (it == null || it.isEmpty()) {
                //TODO: implement time check to refresh DB
                updateDBFromApi()
            } else {
                mChampionsLive.removeSource(champions)
                mChampionsLive.value = it
            }
        }
        return mChampionsLive
    }

    fun getChampion(id: Int): LiveData<Champion> {
        return championRepository.get(id)
    }


    override fun onCleared() {
        super.onCleared()
        championRepository.completableJob.cancel()
    }
}