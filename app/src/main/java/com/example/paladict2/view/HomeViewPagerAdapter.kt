package com.example.paladict2.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.paladict2.view.homeviewpager.HomeFragment
import com.example.paladict2.view.homeviewpager.HomeFriendFragment
import com.example.paladict2.view.homeviewpager.HomeMatchHistoryFragment
import com.example.paladict2.view.homeviewpager.HomeStatFragment

class HomeViewPagerAdapter(fragmentManager: FragmentManager?) : FragmentPagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return HomeStatFragment()
            1 -> return HomeFriendFragment()
            2 -> return HomeMatchHistoryFragment()
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return (getItem(position) as HomeFragment).title
    }

}
