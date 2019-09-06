package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    val sessionRepository = SessionRepository()
    val session = sessionRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        sessionRepository.completableJob.cancel()
    }
}