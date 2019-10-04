package com.example.paladict2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.viewmodel.repositories.PlayerSearchRepository

class PlayerSearchViewModel : ViewModel() {

    private val playerSearchRepository =
        PlayerSearchRepository()

    val combinedPlayerSearchData = MutableLiveData<MergedPlayerSearchData>()


    override fun onCleared() {
        super.onCleared()
        playerSearchRepository.completableJob.cancel()
    }

    val players: LiveData<List<Player>> = Transformations.switchMap(combinedPlayerSearchData) {
        playerSearchRepository.getMutableLiveData(it.session, it.playerName)
    }

}

