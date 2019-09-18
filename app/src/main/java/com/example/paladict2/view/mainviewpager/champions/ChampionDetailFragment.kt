package com.example.paladict2.view.mainviewpager.champions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.paladict2.Constants.Companion.PALADINS_SELECTED_CHAMPION
import com.example.paladict2.R
import com.example.paladict2.databinding.ChampionDetailPageBinding
import com.example.paladict2.model.Champion
import com.example.paladict2.viewmodel.ChampionViewModel
import com.example.paladict2.viewmodel.ChampionViewModelFactory

class ChampionDetailFragment : Fragment() {
    private lateinit var championViewModel: ChampionViewModel

    companion object {
        fun newInstance(title: String, champion : Champion): Fragment {
            val fragment = ChampionDetailFragment()
            val args = Bundle()
            args.putSerializable(PALADINS_SELECTED_CHAMPION, champion)
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
        val args : ChampionDetailFragmentArgs by navArgs()
        val binding : ChampionDetailPageBinding = DataBindingUtil.inflate(inflater, R.layout.champion_detail_page, container, false)
        val myView = binding.root
        val selectedChampion = args.champion
        val champion = ChampionViewModel(selectedChampion)
        binding.champViewModel = champion

        return myView
    }
}
