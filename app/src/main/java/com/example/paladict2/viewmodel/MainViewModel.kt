package com.example.paladict2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.paladict2.Constants
import com.example.paladict2.model.Champion
import com.example.paladict2.model.Item
import com.example.paladict2.model.PaladictDatabase
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.viewmodel.repositories.ChampionRepository
import com.example.paladict2.viewmodel.repositories.ItemRepository
import org.jetbrains.anko.toast

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
        championRepository.updateDBFromApi(session!!)
    }

    private fun updateItemDBFromApi() {
        val session = SessionManager.retrieveSessionID(getApplication())
        itemRepository.updateDBFromApi(session!!)
    }

    private fun getAllChampions() : LiveData<List<Champion>> {
        val prefs = getApplication<Application>().getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
        val prevDBUpdateTime = prefs.getLong(Constants.DB_UPDATE_TIME, 0)
        val timeToUpdate = System.currentTimeMillis() > prevDBUpdateTime + 18000000

        val champions = Transformations.distinctUntilChanged(championRepository.championDao.getAllChampions())

        mChampionsLive.addSource(champions) {
            if (it == null || it.isEmpty() || timeToUpdate) {
                updateChampionDBFromApi()
                prefs.edit().putLong(Constants.DB_UPDATE_TIME, System.currentTimeMillis()).apply()

                //getApplication<Application>().toast("Champion Database Updated!")
            } else {
                mChampionsLive.removeSource(champions)
                mChampionsLive.value = it
            }
        }
        return mChampionsLive
    }

    private fun getAllItems() : LiveData<List<Item>> {
        val prefs = getApplication<Application>().getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
        val prevDBUpdateTime = prefs.getLong(Constants.DB_UPDATE_TIME, 0)
        val timeToUpdate = System.currentTimeMillis() > prevDBUpdateTime + 18000000

        val items = Transformations.distinctUntilChanged(itemRepository.itemDao.getAllItems())

        mItemsLive.addSource(items) {
            if (it == null || it.isEmpty() || timeToUpdate) {
                updateItemDBFromApi()
                prefs.edit().putLong(Constants.DB_UPDATE_TIME, System.currentTimeMillis()).apply()
                //getApplication<Application>().toast("Item Database Updated!")

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