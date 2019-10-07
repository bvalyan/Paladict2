package com.example.paladict2.view.homeviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.databinding.ChampionDetailPageBinding
import com.example.paladict2.databinding.FriendFragmentLayoutBinding
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.viewmodel.ChampionViewModel
import com.example.paladict2.viewmodel.FriendListViewModel
import com.example.paladict2.viewmodel.PlayerSearchViewModel
import com.example.paladict2.viewmodel.PlayerViewModel
import com.example.paladict2.viewmodel.factories.FriendListViewModelFactory
import com.example.paladict2.viewmodel.factories.PlayerSearchViewModelFactory
import kotlinx.android.synthetic.main.friend_fragment_layout.*

class HomeFriendFragment : HomeFragment(), SessionCallback {
    override var title = "FRIENDS"
    private lateinit var friendListViewModel: FriendListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FriendFragmentLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.friend_fragment_layout, container, false)
        val myView = binding.root
        //val selectedChampion = args.champion
        initializeViewModels()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.friendListViewModel = friendListViewModel
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        LoginManager.retrievedLoggedInPlayer(context)

        friendListViewModel.players.observe(viewLifecycleOwner, Observer {
            val friendAdapter = FriendListRecyclerAdapter(it)
            friend_recycler.layoutManager = LinearLayoutManager(context)
            friend_recycler.adapter = friendAdapter
        })

        if (SessionManager.isSessionValid(context!!)) {
            renderFriendList()
        } else {
            SessionManager.createAndSaveSession(context!!, viewLifecycleOwner, this)
        }
    }

    private fun renderFriendList() {
        val userData = MergedPlayerSearchData()
        val loggedInUser = LoginManager.retrievedLoggedInPlayer(context!!)

        userData.playerID = loggedInUser.playerID!!
        userData.session = SessionManager.retrieveSessionID(context!!)!!

        friendListViewModel.combinedPlayerSearchData.value = userData
    }

    private fun initializeViewModels() {
        friendListViewModel = ViewModelProviders.of(
            this,
            FriendListViewModelFactory()
        )
            .get(FriendListViewModel::class.java)
    }

    override fun postSessionExecution() {
        renderFriendList()
    }

    override fun postLogin(isLoggedIn: Boolean) {
        //
    }

}
