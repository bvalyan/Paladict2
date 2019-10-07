package com.example.paladict2.viewmodel.factories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.viewmodel.MainViewModel

class MainViewModelFactory(private val sessionID: String, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(sessionID, application) as T
    }
}