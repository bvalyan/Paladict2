package com.example.paladict2.view.maindrawerviews

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Champion
import com.example.paladict2.view.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.champion_card_single.view.*

class PaladinsChampionRecyclerAdapter(
    private val allChampions: List<Champion>?,
    val fragment: ChampionPageFragment
) :
    RecyclerView.Adapter<PaladinsChampionRecyclerAdapter.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindChampionCard(allChampions!![position], fragment)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.champion_card_single)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return allChampions?.size!!
    }

    class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        private var view : View = v

        fun bindChampionCard(
            champion: Champion,
            fragment: ChampionPageFragment
        ){
            view.champion_name.text = champion.name
            view.champion_class.text = champion.roles
            Picasso.get().load(champion.iconURL).into(view.champion_card_view)
            view.learn_more_link.setOnClickListener {
                fragment.openChampionDetailFragment(champion)
            }
        }
    }

}
