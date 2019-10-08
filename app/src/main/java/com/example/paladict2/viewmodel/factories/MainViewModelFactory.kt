package com.example.paladict2.viewmodel.factories

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.viewmodel.MainViewModel

class MainViewModelFactory(private val application: Application, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(application, context) as T
    }
}