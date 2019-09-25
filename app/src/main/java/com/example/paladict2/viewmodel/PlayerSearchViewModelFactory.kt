package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerSearchViewModelFactory(private val sessionID : String, private val portalID : String, private  val playerID : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerSearchViewModel(sessionID, portalID, playerID) as T
    }
}