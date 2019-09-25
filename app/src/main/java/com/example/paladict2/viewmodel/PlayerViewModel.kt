package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel

class PlayerViewModel(session : String, portalID : String, player : String) : ViewModel(){

    val playerRepository = PlayerRepository()
    val player = playerRepository.getMutableLiveData(session, portalID, player)

    override fun onCleared() {
        super.onCleared()
        playerRepository.completableJob.cancel()
    }
}