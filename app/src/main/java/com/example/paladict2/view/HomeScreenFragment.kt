package com.example.paladict2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.paladict2.R
import com.example.paladict2.viewmodel.MainViewModel

class HomeScreenFragment : Fragment() {

    //2
    companion object {

        fun newInstance(): HomeScreenFragment {

            return HomeScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var mainViewModel: MainViewModel

        activity?.let {
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
            var allChampions = mainViewModel.champions.value
        }


    }

}