package com.example.paladict2.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Item
import com.example.paladict2.view.maindrawerviews.PaladinsChampionRecyclerAdapter

class PaladinsItemRecyclerAdapter(it: List<Item>?, itemFragment: ItemFragment) : RecyclerView.Adapter<PaladinsItemRecyclerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_card_single)
        return PaladinsItemRecyclerAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(v :View) : RecyclerView.ViewHolder(v) {
        private var view = v

        fun bindItemCard(){

        }
    }

}
