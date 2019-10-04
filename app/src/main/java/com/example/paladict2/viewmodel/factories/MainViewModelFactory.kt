package com.example.paladict2.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.viewmodel.MainViewModel

class MainViewModelFactory(private val sessionID: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(sessionID) as T
    }
}