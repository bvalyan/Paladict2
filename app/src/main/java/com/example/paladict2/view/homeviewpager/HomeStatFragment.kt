package com.example.paladict2.view.homeviewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.R
import com.example.paladict2.model.Match
import com.example.paladict2.model.MergedQueueSearchData
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.viewmodel.MatchHistoryViewModel
import com.example.paladict2.viewmodel.factories.MatchHistoryViewModelFactory

class HomeStatFragment : HomeFragment(), SessionCallback {

    override fun postLogin(isLoggedIn: Boolean) {
        //
    }

    override var title = "HOME"
    private lateinit var matchHistoryViewModel: MatchHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.stat_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModels()

        val chartData = MergedQueueSearchData()

        chartData.playerID = LoginManager.retrievedLoggedInPlayer(context).playerID!!
        chartData.sessionID = retrieveSessionID(context!!)!!

        matchHistoryViewModel.mergedMatchHistoryData.value = chartData
    }

    private fun initializeViewModels() {
        matchHistoryViewModel = ViewModelProviders.of(
            this,
            MatchHistoryViewModelFactory(
            )
        )
            .get(MatchHistoryViewModel::class.java)

        matchHistoryViewModel.matches.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
    }

    private fun renderData(matchList: List<Match>?) {
        Log.d("recentmatches", matchList.toString())
    }

    override fun postSessionExecution() {
        //TODO: session control
    }

}
