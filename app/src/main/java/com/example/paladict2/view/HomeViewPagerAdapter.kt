package com.example.paladict2.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class HomeViewPagerAdapter(val context: Context?) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutModelObject = HomePageLayoutModel.values()[position]
        val layout = LayoutInflater.from(container.context)
            .inflate(layoutModelObject.layoutResId, container, false)
        container.addView(layout)

        return layout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return HomePageLayoutModel.values().size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val homePagerEnum = HomePageLayoutModel.values()[position]
        return context?.getString(homePagerEnum.titleResId)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
