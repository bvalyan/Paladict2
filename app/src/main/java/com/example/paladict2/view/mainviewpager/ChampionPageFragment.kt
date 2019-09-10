package com.example.paladict2.view.mainviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paladict2.R
import com.example.paladict2.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_menu_champion_page.*

class ChampionPageFragment : Fragment() {
    companion object {
        fun newInstance(title: String): Fragment {
            val fragment = ChampionPageFragment()
            val args = Bundle()
            args.putString("title", title)
            fragment.arguments = args
            return fragment
        }
    }

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
            mainViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
            val allChampions = mainViewModel.champions.value
            val recyclerAdapter = PaladinsChampionRecyclerAdapter(allChampions)
            val linearLayoutManager = LinearLayoutManager(context)
            champion_recycler.layoutManager = linearLayoutManager
            champion_recycler.adapter = recyclerAdapter
        }
    }
}