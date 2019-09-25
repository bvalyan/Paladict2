package com.example.paladict2.view

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.Constants
import com.example.paladict2.KotlinUtils.Companion.retrievePortalID
import com.example.paladict2.R
import com.example.paladict2.model.Platform
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.viewmodel.PlayerSearchViewModel
import com.example.paladict2.viewmodel.PlayerSearchViewModelFactory
import com.example.paladict2.viewmodel.PlayerViewModel
import com.example.paladict2.viewmodel.PlayerViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.home_screen_fragment.*

class HomeScreenFragment : Fragment(), SessionCallback {

    private var sharedPreferences: SharedPreferences? = null
    private var player = Player()
    private var searchedPlayers = listOf<Player>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = context!!.getSharedPreferences(Constants.SHARED_PREF_NAME, 0)
        if (SessionManager.isSessionValid(sharedPreferences!!)) {
            setupLogin()
        } else {
            SessionManager.createAndSaveSession(sharedPreferences!!, this)
        }
    }

    private fun setupLogin() {
        toggle_button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            login_btn.isEnabled = isChecked
        }

        var playerSearchViewModel: PlayerSearchViewModel

        login_btn.setOnClickListener {
            activity?.let {
                lateinit var platform: Platform
                val userName: String = user_name_input.text.toString()

                when (toggle_button_group.checkedButtonId) {
                    R.id.ps4 -> platform = Platform.PS4
                    R.id.nin_switch -> platform = Platform.Switch
                    R.id.xbox -> platform = Platform.Xbox
                    R.id.pc -> platform = Platform.PC
                }

                playerSearchViewModel = ViewModelProviders.of(
                    this,
                    PlayerSearchViewModelFactory(
                        retrieveSessionID(context!!) as String,
                        retrievePortalID(platform),
                        userName
                    )
                ).get(PlayerSearchViewModel::class.java)

                playerSearchViewModel.players.observe(this, Observer {
                    searchedPlayers = playerSearchViewModel.players.value as ArrayList<Player>
                    renderSearchedOptions(searchedPlayers)
                })


            }
        }

    }

    private fun renderSearchedOptions(players: List<Player>) {
        var builder = MaterialAlertDialogBuilder(context)
        var dialogView = layoutInflater.inflate(R.layout.player_search_dialog, null)

        builder.setView(dialogView)

        var playerSearchResultAdapter = PlayerSearchResultAdapter(players)
    }


    override fun postSessionExecution() {
        setupLogin()
    }

}