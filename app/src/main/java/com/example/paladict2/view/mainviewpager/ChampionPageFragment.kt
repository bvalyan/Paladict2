package com.example.paladict2.view.mainviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.model.Champion
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import kotlinx.android.synthetic.main.main_menu_champion_page.*

class ChampionPageFragment : Fragment() {

    private var allChampions = ArrayList<Champion>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_champion_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainViewModel: MainViewModel

        activity?.let {
            mainViewModel = ViewModelProviders.of(
                this,
                MainViewModelFactory(
                    retrieveSessionID(
                        context!!
                    ) as String
                )
            )
                .get(MainViewModel::class.java)
            mainViewModel.champions.observe(this, Observer {
                allChampions = mainViewModel.champions.value as ArrayList<Champion>
                val recyclerAdapter = PaladinsChampionRecyclerAdapter(allChampions, this)
                val linearLayoutManager = LinearLayoutManager(context)
                champion_recycler.layoutManager = linearLayoutManager
                champion_recycler.adapter = recyclerAdapter
            })
        }
    }

    fun openChampionDetailFragment(champion: Champion) {
        val championDetail = ChampionPageFragmentDirections.actionChampionPageFragmentToChampionDetailFragment(champion)
        findNavController().navigate(championDetail)
    }
}