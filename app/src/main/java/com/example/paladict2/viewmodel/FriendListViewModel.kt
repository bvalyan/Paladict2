package com.example.paladict2.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.paladict2.Constants
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.utils.JavaUtils
import com.example.paladict2.utils.KotlinUtils
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.viewmodel.repositories.FriendListRepository

class FriendListViewModel(application: Application) : AndroidViewModel(application) {

    private val friendListRepository = FriendListRepository()
    val combinedPlayerSearchData = MutableLiveData<MergedPlayerSearchData>()

    init {
        combinedPlayerSearchData.value?.session = SessionManager.retrieveSessionID(application.applicationContext)!!
        combinedPlayerSearchData.value?.playerID = LoginManager.retrievedLoggedInPlayer(application.applicationContext).playerID!!
    }

    override fun onCleared() {
        super.onCleared()
        friendListRepository.completableJob.cancel()
    }

    val players: LiveData<List<Player>> = Transformations.switchMap(combinedPlayerSearchData) {
        friendListRepository.getFriendMutableLiveData(it.session, it.playerID)
    }
}
