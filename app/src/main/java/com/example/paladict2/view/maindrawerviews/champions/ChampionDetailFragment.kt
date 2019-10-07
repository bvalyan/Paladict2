package com.example.paladict2.view.maindrawerviews.champions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.paladict2.R
import com.example.paladict2.databinding.ChampionDetailPageBinding
import com.example.paladict2.viewmodel.ChampionViewModel
import kotlinx.android.synthetic.main.champion_detail_page.*

class ChampionDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: ChampionDetailFragmentArgs by navArgs()
        val binding: ChampionDetailPageBinding =
            DataBindingUtil.inflate(inflater, R.layout.champion_detail_page, container, false)
        val myView = binding.root
        val selectedChampion = args.champion
        val champion = ChampionViewModel(selectedChampion)
        binding.champViewModel = champion

        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChampionAbilityRecyclerAdapter()
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(champion_ability_recycler)
        champion_ability_recycler.layoutManager = LinearLayoutManager(context)
        champion_ability_recycler.adapter = adapter
    }
}
