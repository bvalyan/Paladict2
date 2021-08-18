package com.example.paladict2.view.maindrawerviews.playersearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.paladict2.R
import com.example.paladict2.databinding.PlayerSearchResultStatPageBinding
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.viewmodel.PlayerViewModel
import com.example.paladict2.viewmodel.factories.PlayerViewModelFactory

class PlayerResultFragment : Fragment() {

    private var selectedPlayerViewModel = PlayerViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: PlayerSearchResultStatPageBinding = DataBindingUtil.inflate(
            inflater, R.layout.player_search_result_stat_page, container, false
        );
        val view = binding.root
        binding.playerviewmodel = selectedPlayerViewModel
        return view
    }

    private val args: PlayerResultFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val playerID = args.playerID
        initializeViewModels()
        setupObservers()

        val selectedPlayerData = MergedPlayerSearchData()

        selectedPlayerData.playerID = playerID
        selectedPlayerData.session = SessionManager.retrieveSessionID(context)

        selectedPlayerViewModel.combinedPlayerSearchData.value = selectedPlayerData
    }

    private fun initializeViewModels() {
        activity.let {
            selectedPlayerViewModel = ViewModelProvider(
                this,
                PlayerViewModelFactory(
                )
            )
                .get(PlayerViewModel::class.java)
        }
    }

    private fun setupObservers() {
        selectedPlayerViewModel.player.observe(viewLifecycleOwner, {
            Log.d("", it.activePlayerID!!)

        })
    }
}
