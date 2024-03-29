package com.example.paladict2.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.viewmodel.PlayerSearchViewModel

class PlayerSearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayerSearchViewModel() as T
    }
}
