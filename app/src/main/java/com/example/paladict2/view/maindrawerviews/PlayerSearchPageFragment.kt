package com.example.paladict2.view.maindrawerviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paladict2.R
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.viewmodel.PlayerSearchViewModel
import com.example.paladict2.viewmodel.factories.PlayerSearchViewModelFactory
import kotlinx.android.synthetic.main.main_menu_player_search_page.*

class PlayerSearchPageFragment : Fragment() {

    private var playerSearchViewModel = PlayerSearchViewModel()
    private var searchedPlayers: List<Player?> = listOf<Player>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_player_search_page, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModels()
        setupObservers()
        handleSearchQuery()
        setupSearchResultRecycler()
    }

    private fun setupSearchResultRecycler() {
        val playerSearchResultAdapter = CompPlayerSearchAdapter(emptyList())
        player_search_recycler.adapter = playerSearchResultAdapter
    }

    private fun initViewModels() {
        activity.let {
            playerSearchViewModel = ViewModelProvider(
                this,
                PlayerSearchViewModelFactory()
            )
                .get(PlayerSearchViewModel::class.java)
        }
    }

    private fun setupObservers() {
        playerSearchViewModel.players.observe(viewLifecycleOwner, Observer {
            searchedPlayers = it
            renderSearchedOptions(searchedPlayers)
        })
    }

    private fun renderSearchedOptions(searchedPlayers: List<Player?>) {
        this.searchedPlayers = searchedPlayers
        player_search_recycler.adapter?.notifyDataSetChanged()
    }

    private fun handleSearchQuery() {
        player_search_view.setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val searchData = MergedPlayerSearchData()

                searchData.playerName = query
                searchData.session = SessionManager.retrieveSessionID(context)
                playerSearchViewModel.combinedPlayerSearchData.value = searchData
                return true
            }
        })
    }
}
