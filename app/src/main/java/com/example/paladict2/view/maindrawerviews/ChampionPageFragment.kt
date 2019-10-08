package com.example.paladict2.view.maindrawerviews

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
import com.example.paladict2.networking.SessionManager.Companion.createAndSaveSession
import com.example.paladict2.networking.SessionManager.Companion.isSessionValid
import com.example.paladict2.networking.SessionManager.Companion.retrieveSessionID
import com.example.paladict2.view.SessionCallback
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import kotlinx.android.synthetic.main.main_menu_champion_page.*

class ChampionPageFragment : Fragment(), SessionCallback {
    override fun postLogin(isLoggedIn: Boolean) {
        //
    }

    override fun postSessionExecution() {
        initializeViewModel()
    }

    private var allChampions = listOf<Champion>()
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_champion_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isSessionValid(context!!)) {
            initializeViewModel()
        } else {
            createAndSaveSession(context!!, viewLifecycleOwner, this)
        }

    }

    private fun initializeViewModel() {
        activity.let {
            mainViewModel = ViewModelProviders.of(
                this,
                MainViewModelFactory(
                    activity!!.application,
                    context!!
                )
            )
                .get(MainViewModel::class.java)

            //mainViewModel.getAllChampions(retrieveSessionID(context!!)!!)

            mainViewModel.mChampionsLive.observe(viewLifecycleOwner, Observer {
                val recyclerAdapter = PaladinsChampionRecyclerAdapter(it, this)
                val linearLayoutManager = LinearLayoutManager(context)
                champion_recycler.layoutManager = linearLayoutManager
                champion_recycler.adapter = recyclerAdapter
            })
        }
    }

    fun openChampionDetailFragment(champion: Champion) {
        val championDetail =
            ChampionPageFragmentDirections.actionChampionPageFragmentToChampionDetailFragment(
                champion
            )
        findNavController().navigate(championDetail)
    }
}