package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerViewModelFactory(private val sessionID: String, private val playerID: String) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerViewModel(sessionID, playerID) as T
    }
}
