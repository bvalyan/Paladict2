package com.example.paladict2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.paladict2.R
import kotlinx.android.synthetic.main.home_screen_fragment.*

class HomeScreenFragment : Fragment() {

    //2
    companion object {
        fun newInstance(title: String): Fragment {
            val fragment = HomeScreenFragment()
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
        return inflater.inflate(R.layout.home_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainMenuViewPager = main_menu_viewpager
        val mainPagerAdapter = MainMenuPagerAdapter(activity!!.supportFragmentManager)
        mainMenuViewPager.adapter = mainPagerAdapter
        main_tabs.setupWithViewPager(mainMenuViewPager)
    }

}