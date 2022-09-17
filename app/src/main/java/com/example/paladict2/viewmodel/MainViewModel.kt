package com.example.paladict2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.example.paladict2.Constants
import com.example.paladict2.R
import com.example.paladict2.model.Champion
import com.example.paladict2.model.Item
import com.example.paladict2.model.PaladictDatabase
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.toast
import com.example.paladict2.viewmodel.repositories.ChampionRepository
import com.example.paladict2.viewmodel.repositories.ItemRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val championRepository: ChampionRepository
    private val itemRepository: ItemRepository
    val mChampionsLive = MediatorLiveData<List<Champion>>()
    val mItemsLive = MediatorLiveData<List<Item>>()

    init {
        val championDao = PaladictDatabase.getInstance(application).championDao()
        val itemDao = PaladictDatabase.getInstance(application).itemDao()
        itemRepository = ItemRepository(itemDao)
        championRepository = ChampionRepository(championDao)
        getAllChampions()
        getAllItems()
    }

    private fun updateChampionDBFromApi() {
        val session = SessionManager.retrieveSessionID(getApplication())
        championRepository.updateDBFromApi(session)
    }

    private fun updateItemDBFromApi() {
        val session = SessionManager.retrieveSessionID(getApplication())
        itemRepository.updateDBFromApi(session)
    }

    private fun getAllChampions(): LiveData<List<Champion>> {
        val prefs =
            getApplication<Application>().getSharedPreferences(Constants.SHARED_PREF_NAME, 0)

        val champions = championRepository.championDao.getAllChampions()

        mChampionsLive.addSource(champions) {
            val prevDBUpdateTime = prefs.getLong(Constants.DB_UPDATE_TIME, 0)
            val timeToUpdate = System.currentTimeMillis() > prevDBUpdateTime + Constants.DB_UPDATE_VALUE
            if (it == null || it.isEmpty() || timeToUpdate) {
                updateChampionDBFromApi()
                toast(getApplication()).setText(getApplication<Application>().getString(R.string.champion_db_updated))
                prefs.edit().putLong(Constants.DB_UPDATE_TIME, System.currentTimeMillis()).apply()
            } else {
                mChampionsLive.removeSource(champions)
                mChampionsLive.value = it
            }
        }
        return mChampionsLive
    }

    private fun getAllItems(): LiveData<List<Item>> {
        val prefs =
            getApplication<Application>().getSharedPreferences(Constants.SHARED_PREF_NAME, 0)

        val items = itemRepository.itemDao.getAllItems()

        mItemsLive.addSource(items) {
            val prevItemDBUpdateTime = prefs.getLong(Constants.DB_UPDATE_TIME, 0)
            val timeToUpdateItems = System.currentTimeMillis() > prevItemDBUpdateTime + 18000000
            if (it == null || it.isEmpty() || timeToUpdateItems) {
                updateItemDBFromApi()
                toast(getApplication()).setText(getApplication<Application>().getString(R.string.item_db_updated))
                prefs.edit().putLong(Constants.DB_UPDATE_TIME, System.currentTimeMillis()).apply()
            } else {
                mItemsLive.removeSource(items)
                mItemsLive.value = it
            }
        }
        return mItemsLive
    }

    override fun onCleared() {
        super.onCleared()
        championRepository.completableJob.cancel()
    }
}