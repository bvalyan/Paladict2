package com.example.paladict2.view

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.Constants
import com.example.paladict2.KotlinUtils.Companion.retrievePortalID
import com.example.paladict2.R
import com.example.paladict2.model.Platform
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.viewmodel.PlayerSearchViewModel
import com.example.paladict2.viewmodel.PlayerSearchViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.home_screen_fragment.*
import kotlinx.android.synthetic.main.player_search_dialog.view.*

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
            SessionManager.createAndSaveSession(sharedPreferences!!, this, this)
        }
    }

    private fun setupLogin() {
        toggle_button_group.addOnButtonCheckedListener { group, _, _ ->
            login_btn.isEnabled = !user_name_input.text.isNullOrBlank() && group.checkedButtonIds.isNotEmpty()
        }

        user_name_input.addTextChangedListener {
            login_btn.isEnabled =
                !it.isNullOrBlank() && toggle_button_group.checkedButtonIds.isNotEmpty()
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

             playerSearchViewModel = PlayerSearchViewModel(retrieveSessionID(context!!)!!, retrievePortalID(platform), userName)


                playerSearchViewModel.players.observe(this, Observer {
                    searchedPlayers = playerSearchViewModel.players.value as ArrayList<Player>
                    renderSearchedOptions(searchedPlayers)
                })


            }
        }

    }

    private fun renderSearchedOptions(players: List<Player>) {
        val builder = MaterialAlertDialogBuilder(context)
        val dialogView = layoutInflater.inflate(R.layout.player_search_dialog, null)

        builder.setView(dialogView)

        val playerSearchResultAdapter =
            PlayerSearchResultAdapter(players) { item ->
                enablePlayerSelectionBtn(
                    item,
                    dialogView
                )
            }

        val dividerItemDecoration = DividerItemDecoration(context, VERTICAL)

        dialogView.search_result_recycler.layoutManager = LinearLayoutManager(context)
        dialogView.search_result_recycler.adapter = playerSearchResultAdapter
        dialogView.search_result_recycler.addItemDecoration(dividerItemDecoration)

        val alertDialog = builder.create()

        alertDialog.show()

        dialogView.player_search_ok_btn.isEnabled = false
    }


    override fun postSessionExecution() {
        setupLogin()
    }

    private fun enablePlayerSelectionBtn(item: Any, dialogView: View) {
        val selectedPlayer = item as Player
        dialogView.player_search_ok_btn.isEnabled = true

        dialogView.player_search_ok_btn.setOnClickListener {
            Log.d(
                "selectedPlayer",
                selectedPlayer.name + selectedPlayer.level + selectedPlayer.region
            )
        }
    }

}