package com.example.paladict2.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.example.paladict2.Constants
import com.example.paladict2.model.Champion
import com.example.paladict2.model.PaladictDatabase
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.toast
import com.example.paladict2.viewmodel.repositories.ChampionRepository
import org.jetbrains.anko.toast

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val championRepository: ChampionRepository
    val mChampionsLive = MediatorLiveData<List<Champion>>()



    init {
        val championDao = PaladictDatabase.getInstance(application).championDao()
        championRepository = ChampionRepository(championDao)
        getAllChampions()
    }



    private fun updateDBFromApi() {
        val session = SessionManager.retrieveSessionID(getApplication())
        championRepository.updateDBFromApi(session!!)
    }

    private fun getAllChampions() : LiveData<List<Champion>> {
        val prefs = getApplication<Application>().getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
        val prevDBUpdateTime = prefs.getLong(Constants.DB_UPDATE_TIME, 0)
        val timeToUpdate = System.currentTimeMillis() > prevDBUpdateTime + 18000000

        val champions = Transformations.distinctUntilChanged(championRepository.championDao.getAllChampions())

        mChampionsLive.addSource(champions) {
            if (it == null || it.isEmpty() || timeToUpdate) {
                updateDBFromApi()
                prefs.edit().putLong(Constants.DB_UPDATE_TIME, System.currentTimeMillis()).apply()
                getApplication<Application>().toast("Database Updated!")

            } else {
                mChampionsLive.removeSource(champions)
                mChampionsLive.value = it
            }
        }
        return mChampionsLive
    }

    override fun onCleared() {
        super.onCleared()
        championRepository.completableJob.cancel()
    }
}