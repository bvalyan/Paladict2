package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel

class PlayerViewModel(session : String, player : String) : ViewModel(){

    private val playerRepository = PlayerRepository()
    val player = playerRepository.getMutableLiveData(session, player)

    override fun onCleared() {
        super.onCleared()
        playerRepository.completableJob.cancel()
    }


}