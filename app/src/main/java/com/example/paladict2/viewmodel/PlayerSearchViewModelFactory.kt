package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

<<<<<<< HEAD
class PlayerSearchViewModelFactory(private val sessionID : String, private val portalID : String, private  val playerName : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerSearchViewModel(sessionID, portalID, playerName) as T
=======
class PlayerSearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlayerSearchViewModel() as T
>>>>>>> c79cf97bc74a4386b5cc2b3fa210d10f554a40fa
    }
}