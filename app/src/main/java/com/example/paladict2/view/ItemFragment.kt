package com.example.paladict2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.homeviewpager.HomeFragment
import com.example.paladict2.view.maindrawerviews.PaladinsChampionRecyclerAdapter
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import kotlinx.android.synthetic.main.main_menu_champion_page.*

class ItemFragment : HomeFragment(), SessionCallback {
    override fun postLogin(isLoggedIn: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postSessionExecution() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_item_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (SessionManager.isSessionValid(context!!)) {
            initializeViewModel()
        } else {
            SessionManager.createAndSaveSession(context!!, viewLifecycleOwner, this)
        }
    }

    private fun initializeViewModel() {
        activity.let {
            itemViewModel = ViewModelProvider(
                this,
                MainViewModelFactory(
                    activity!!.application
                )
            )
                .get(MainViewModel::class.java)

            mainViewModel.mChampionsLive.observe(viewLifecycleOwner, Observer {
                val recyclerAdapter = PaladinsChampionRecyclerAdapter(it, this)
                val linearLayoutManager = LinearLayoutManager(context)
                champion_recycler.layoutManager = linearLayoutManager
                champion_recycler.adapter = recyclerAdapter
            })
        }
    }
}