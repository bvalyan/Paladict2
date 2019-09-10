package com.example.paladict2.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.paladict2.view.mainviewpager.ChampionPageFragment
import com.example.paladict2.view.mainviewpager.ItemPageFragment
import com.example.paladict2.view.mainviewpager.NewsPageFragment
import com.example.paladict2.view.mainviewpager.PlayerSearchPageFragment

class MainMenuPagerAdapter(supportFragmentManager : FragmentManager) : FragmentStatePagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return ChampionPageFragment.newInstance("Champions")
            1 -> return PlayerSearchPageFragment.newInstance("Players")
            2 -> return ItemPageFragment.newInstance("Items")
            3 -> return NewsPageFragment.newInstance("News")
        }
        return ChampionPageFragment.newInstance("")
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> return "Champs"
            1 -> return "Players"
            2 -> return "Items"
            3 -> return "News"
        }
        return ""
    }

    override fun getCount(): Int {
        return 4
    }
}