package com.example.paladict2.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.Constants
import com.example.paladict2.R
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.utils.LoginManager
import com.example.paladict2.viewmodel.PlayerSearchViewModel
import com.example.paladict2.viewmodel.PlayerViewModel
import com.example.paladict2.viewmodel.factories.PlayerSearchViewModelFactory
import com.example.paladict2.viewmodel.factories.PlayerViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.home_screen_fragment.*
import kotlinx.android.synthetic.main.player_search_dialog.view.*

class HomeScreenFragment : Fragment(), SessionCallback {

    private var sharedPreferences: SharedPreferences? = null
    private var searchedPlayers = listOf<Player>()
    private lateinit var playerSearchViewModel: PlayerSearchViewModel
    private lateinit var selectedPlayerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user_view_pager.offscreenPageLimit = 2
        sharedPreferences = context!!.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)

        initializeViewModels()

        playerSearchViewModel.players.observe(viewLifecycleOwner, Observer {
            if(!LoginManager.isLoggedIn(context!!)) {
                searchedPlayers = playerSearchViewModel.players.value as ArrayList<Player>
                renderSearchedOptions(searchedPlayers)
            }
        })

        selectedPlayerViewModel.player.observe(viewLifecycleOwner, Observer {
            if (!LoginManager.isLoggedIn(context))
                saveSelectedPlayerAsLogin(it.name, it.activePlayerID, it.platform)
        })

        if (SessionManager.isSessionValid(context!!)) {
            setupUI()
        } else {
            SessionManager.createAndSaveSession(context!!, this, this)
        }
    }

    private fun initializeViewModels() {
        playerSearchViewModel = ViewModelProviders.of(
            this,
            PlayerSearchViewModelFactory()
        )
            .get(PlayerSearchViewModel::class.java)

        selectedPlayerViewModel = ViewModelProviders.of(
            this,
            PlayerViewModelFactory(
            )
        )
            .get(PlayerViewModel::class.java)
    }

    private fun setupUI() {
        if (!LoginManager.isLoggedIn(context)) {
            setupLogin()
        } else {
            val loggedInPlayer = LoginManager.retrievedLoggedInPlayer(context)
            createHomeUI(loggedInPlayer)
        }
    }

    private fun saveSelectedPlayerAsLogin(name: String?, playerID: String?, platform: String?) {
        val sharedPreferences: SharedPreferences? =
            activity!!.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)

        sharedPreferences!!.edit().putString(Constants.PLAYER_NAME, name)
            .putString(Constants.PLAYER_ID, playerID)
            .putString(Constants.PLATFORM, platform)
            .apply()
    }

    private fun setUpLoggedInDisplay() {
        login_page_group.visibility = GONE
        logged_in_group.visibility = VISIBLE
    }

    private fun setUpLoginDisplay(){
        login_page_group.visibility = VISIBLE
        logged_in_group.visibility = GONE
    }

    private fun setupLogin() {
        setUpLoginDisplay()

        user_name_input.addTextChangedListener {
            login_btn.isEnabled =
                !it.isNullOrBlank()
        }

        login_btn.setOnClickListener {
            val userName: String = user_name_input.text.toString()

            val searchData = MergedPlayerSearchData()

            searchData.playerName = userName
            searchData.session = retrieveSessionID(context!!)!!

            playerSearchViewModel.combinedPlayerSearchData.value = searchData
        }
    }

    private fun renderSearchedOptions(players: List<Player>) {
        val builder = MaterialAlertDialogBuilder(context)
        val dialogView = layoutInflater.inflate(R.layout.player_search_dialog, null)

        builder.setView(dialogView)

        val alertDialog = builder.create()

        val playerSearchResultAdapter =
            PlayerSearchResultAdapter(players) { item ->
                retrieveSelectedPlayerInfo(
                    item,
                    alertDialog
                )
            }

        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)

        dividerItemDecoration.setDrawable(context!!.getDrawable(R.drawable.divider_drawable)!!)

        dialogView.search_result_recycler.layoutManager = LinearLayoutManager(context)
        dialogView.search_result_recycler.adapter = playerSearchResultAdapter
        dialogView.search_result_recycler.addItemDecoration(dividerItemDecoration)

        alertDialog.show()
    }


    override fun postSessionExecution() {
        setupUI()
    }

    private fun retrieveSelectedPlayerInfo(
        item: Any,
        alertDialog: AlertDialog
    ) {
        val selectedPlayer = item as Player
        alertDialog.dismiss()
        createHomeUI(selectedPlayer)
    }

    private fun createHomeUI(selectedPlayer: Player) {
        setUpLoggedInDisplay()

        val selectedPlayerData = MergedPlayerSearchData()

        selectedPlayerData.playerID = selectedPlayer.playerID!!
        selectedPlayerData.session = retrieveSessionID(context!!)!!

        selectedPlayerViewModel.combinedPlayerSearchData.value = selectedPlayerData

        (activity as SessionCallback).postLogin(true)
        setupHomeViewPager()
    }

    private fun setupHomeViewPager() {
        val homePageAdapter = HomeViewPagerAdapter(childFragmentManager)
        user_view_pager.adapter = homePageAdapter
    }

    override fun postLogin(isLoggedIn: Boolean) {
        //
    }
}


