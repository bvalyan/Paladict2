package com.example.paladict2.view.maindrawerviews.playersearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.viewmodel.PlayerSearchViewModel
import com.example.paladict2.viewmodel.factories.PlayerSearchViewModelFactory
import kotlinx.android.synthetic.main.main_menu_player_search_page.*

class PlayerSearchPageFragment : Fragment() {

    private var playerSearchViewModel = PlayerSearchViewModel()
    private var searchedPlayers: MutableList<Player?> = mutableListOf(Player())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_player_search_page, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchResultRecycler()
        initViewModels()
        setupObservers()
        handleSearchQuery()
    }

    private fun setupSearchResultRecycler() {
        val playerSearchResultAdapter = CompPlayerSearchAdapter(searchedPlayers)
        searchedPlayers.clear()
        val divider = DividerItemDecoration(context, VERTICAL)
        player_search_recycler.addItemDecoration(divider)
        player_search_recycler.adapter = playerSearchResultAdapter
        player_search_recycler.layoutManager = LinearLayoutManager(context)
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
            searchedPlayers.clear()
            searchedPlayers.addAll( it.toMutableList())
            renderSearchedOptions(searchedPlayers)
        })
    }

    private fun renderSearchedOptions(searchedPlayers: List<Player?>) {
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
