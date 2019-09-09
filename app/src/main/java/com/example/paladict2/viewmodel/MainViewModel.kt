package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.paladict2.Utils

class MainViewModel(val sessionID: String) : ViewModel() {


    val sessionRepository = SessionRepository()
    val session = sessionRepository.getMutableLiveData()
    val championRepository = ChampionRepository()
    val champions = championRepository.getMutableLiveData(Utils.getDate(), Utils.createSignature("getchampions"), sessionID)



    override fun onCleared() {
        super.onCleared()
        sessionRepository.completableJob.cancel()
        championRepository.completableJob.cancel()
    }
}