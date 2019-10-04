package com.example.paladict2.view

import com.example.paladict2.R

enum class HomePageLayoutModel(val titleResId: Int, val layoutResId : Int) {
    STATS(R.string.stats_title, R.layout.stat_fragment_layout),
    FRIENDS(R.string.friends_title, R.layout.friend_fragment_layout),
    MATCHES(R.string.matches_title, R.layout.matches_fragment_layout)
}
