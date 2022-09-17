package com.example.paladict2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.networking.SessionManager
import com.example.paladict2.view.homeviewpager.HomeFragment
import com.example.paladict2.viewmodel.MainViewModel
import com.example.paladict2.viewmodel.factories.MainViewModelFactory
import kotlinx.android.synthetic.main.main_menu_item_page.*

class ItemFragment : HomeFragment(), SessionCallback {

    private lateinit var mainViewModel: MainViewModel

    override fun postLogin(isLoggedIn: Boolean) {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun postSessionExecution() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu_item_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (SessionManager.isSessionValid(requireContext())) {
            initializeViewModel()
        } else {
            SessionManager.createAndSaveSession(requireContext(), viewLifecycleOwner, this)
        }
    }

    private fun initializeViewModel() {
        activity.let {
            mainViewModel = ViewModelProvider(
                this,
                MainViewModelFactory(
                    requireActivity().application
                )
            )[MainViewModel::class.java]

            mainViewModel.mItemsLive.observe(
                viewLifecycleOwner,
                Observer {
                    val recyclerAdapter = PaladinsItemRecyclerAdapter(it)
                    val linearLayoutManager = LinearLayoutManager(context)
                    item_recycler.layoutManager = linearLayoutManager
                    item_recycler.adapter = recyclerAdapter
                }
            )
        }
    }
}
