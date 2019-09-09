package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel(session : String) : ViewModel() {

    val championRepository = ChampionRepository()
    val champions = championRepository.getMutableLiveData(session)



    override fun onCleared() {
        super.onCleared()
        championRepository.completableJob.cancel()
    }
}