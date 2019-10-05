package com.example.paladict2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.paladict2.model.Match
import com.example.paladict2.model.MergedQueueSearchData
import com.example.paladict2.viewmodel.repositories.MatchRepository

class MatchHistoryViewModel : ViewModel() {
    var matchRepository = MatchRepository()
    val mergedMatchHistoryData = MutableLiveData<MergedQueueSearchData>()

    override fun onCleared() {
        super.onCleared()
        matchRepository.completableJob.cancel()
    }

    val matches: LiveData<List<Match>> = Transformations.switchMap(mergedMatchHistoryData) {
        matchRepository.getMatchMutableLiveData(it.sessionID, it.playerID)
    }

}