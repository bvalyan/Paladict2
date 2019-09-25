package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel

class PlayerSearchViewModel(session : String, portalID : String, player : String) : ViewModel(){

    val playerSearchRepository = PlayerSearchRepository()
    val players = playerSearchRepository.getMutableLiveData(session, portalID, player)

    override fun onCleared() {
        super.onCleared()
        playerSearchRepository.completableJob.cancel()
    }
}