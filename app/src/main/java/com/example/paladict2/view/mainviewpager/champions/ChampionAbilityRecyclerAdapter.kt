package com.example.paladict2.view.mainviewpager.champions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Ability
import com.example.paladict2.view.BindableAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ability_list_item.view.*

class ChampionAbilityRecyclerAdapter : RecyclerView.Adapter<ChampionAbilityRecyclerAdapter.AbilityHolder>(), BindableAdapter<List<Ability>> {

    private var abilities = emptyList<Ability>()

    override fun setData(items : List<Ability>) {
        abilities = items
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AbilityHolder(inflater.inflate(R.layout.ability_list_item, parent, false))
    }

    override fun getItemCount(): Int {
       return abilities.size
    }

    override fun onBindViewHolder(holder: AbilityHolder, position: Int) {
        holder.bind(abilities[position])
    }


    class AbilityHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(ability: Ability){
            itemView.skill_description_view.text = ability.description
            itemView.skill_title_view.text = ability.summary
            Picasso.get().load(ability.iconImageURL).into(itemView.skill_image_view)
        }

    }
}