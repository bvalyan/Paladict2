package com.example.paladict2.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_card_single.view.*

class PaladinsItemRecyclerAdapter(val it: List<Item>?) : RecyclerView.Adapter<PaladinsItemRecyclerAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItemCard(it!![position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.item_card_single)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
       return it!!.size
    }


    class ViewHolder(v :View) : RecyclerView.ViewHolder(v) {
        private var view = v

        fun bindItemCard(item : Item){
            view.item_name.text = item.itemName
            view.item_description.text = item.itemDescription
            view.item_price.text = view.context.getString(R.string.price, item.price.toString())
            Picasso.get().load(item.itemIconURL).into(view.item_image)
        }
    }

}
