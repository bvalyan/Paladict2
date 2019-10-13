package com.example.paladict2.view.homeviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.databinding.MatchesFragmentLayoutBinding
import com.example.paladict2.model.MergedQueueSearchData
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.view.removeAllDecorations
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.MatchHistoryViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import com.example.paladict2.viewmodel.factories.MatchHistoryViewModelFactory
import kotlinx.android.synthetic.main.matches_fragment_layout.*

class HomeMatchHistoryFragment : HomeFragment(), SessionCallback {
    override fun postLogin(isLoggedIn: Boolean) {
        //
    }

    override fun postSessionExecution() {
        renderMatchHistory()
    }

    private lateinit var matchHistoryViewModel: MatchHistoryViewModel
    private lateinit var championsViewModel: MainViewModel
    override var title = "MATCHES"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MatchesFragmentLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.matches_fragment_layout, container, false)
        val myView = binding.root
        initializeViewModels()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.matchHistoryViewModel = matchHistoryViewModel
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LoginManager.retrievedLoggedInPlayer(context)

        history_swipe_layout.setOnRefreshListener {
            renderOnSession()
        }

        matchHistoryViewModel.matches.observe(viewLifecycleOwner, Observer {
            val matchAdapter =
                MatchHistoryRecyclerAdapter(it, championsViewModel.mChampionsLive.value!!)
            val divider = DividerItemDecoration(context!!, VERTICAL)
            match_recycler.removeAllDecorations()
            divider.setDrawable(context!!.getDrawable(R.drawable.divider_drawable)!!)
            match_recycler.layoutManager = LinearLayoutManager(context)
            match_recycler.addItemDecoration(divider)
            match_recycler.adapter = matchAdapter

            if(history_swipe_layout.isRefreshing){
                history_swipe_layout.isRefreshing = false
            }
        })

        championsViewModel.mChampionsLive.observe(viewLifecycleOwner, Observer {
            renderOnSession()
        })
    }

    private fun renderMatchHistory() {
        val matchSearchData = MergedQueueSearchData()
        val loggedInUser = LoginManager.retrievedLoggedInPlayer(context!!)

        matchSearchData.playerID = loggedInUser.playerID!!
        matchSearchData.sessionID = SessionManager.retrieveSessionID(context!!)!!

        matchHistoryViewModel.mergedMatchHistoryData.value = matchSearchData
    }

    private fun initializeViewModels() {
        activity.let {
            matchHistoryViewModel = ViewModelProvider(
                this,
                MatchHistoryViewModelFactory()
            )
                .get(MatchHistoryViewModel::class.java)

            championsViewModel = ViewModelProvider(
                this,
                MainViewModelFactory(activity!!.application)
            )
                .get(MainViewModel::class.java)
        }
    }

    private fun renderOnSession() {
        if (SessionManager.isSessionValid(context!!)) {
            renderMatchHistory()
        } else {
            SessionManager.createAndSaveSession(context!!, viewLifecycleOwner, this)
        }
    }
}
