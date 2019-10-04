package com.example.paladict2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.viewmodel.repositories.PlayerRepository

class PlayerViewModel : ViewModel(){

    private val playerRepository = PlayerRepository()

    val combinedPlayerSearchData = MutableLiveData<MergedPlayerSearchData>()

    val player: LiveData<Player> = Transformations.switchMap(combinedPlayerSearchData) {
        playerRepository.getMutableLiveData(it.session, it.playerID)
    }

    override fun onCleared() {
        super.onCleared()
        playerRepository.completableJob.cancel()
    }


}