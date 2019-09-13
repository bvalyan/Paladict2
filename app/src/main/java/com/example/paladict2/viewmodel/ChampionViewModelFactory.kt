package com.example.paladict2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.model.Champion

class ChampionViewModelFactory(val champion: Champion) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChampionViewModel(champion) as T
    }
}