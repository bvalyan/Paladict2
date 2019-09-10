package com.example.paladict2.view.mainviewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.paladict2.R

class ItemPageFragment : Fragment() {
    companion object {
        fun newInstance(title: String): Fragment {
            val fragment = ItemPageFragment()
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
        return  inflater.inflate(R.layout.main_menu_item_page, container, false)!!
    }
}
