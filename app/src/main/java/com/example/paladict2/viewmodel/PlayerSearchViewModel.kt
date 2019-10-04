package com.example.paladict2.viewmodel

import androidx.lifecycle.*
import com.example.paladict2.model.Player

class PlayerSearchViewModel : ViewModel() {

    private val playerSearchRepository = PlayerSearchRepository()

    val combinedPlayerSearchData = MutableLiveData<MergedPlayerSearchData>()


    override fun onCleared() {
        super.onCleared()
        playerSearchRepository.completableJob.cancel()
    }

    val players: LiveData<List<Player>> = Transformations.switchMap(combinedPlayerSearchData) {
        playerSearchRepository.getMutableLiveData(it.session, it.playerName)
    }

}

class MergedPlayerSearchData {
    var playerName = String()
    var portalID = String()
    var session = String()
    var playerID = String()
}
