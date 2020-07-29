package com.example.paladict2.view.maindrawerviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Player
import com.example.paladict2.utils.KotlinUtils
import kotlinx.android.synthetic.main.player_single_layout.view.*

class CompPlayerSearchAdapter(private var players: MutableList<Player?>) : RecyclerView.Adapter<PlayerSearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSearchViewHolder {
        return PlayerSearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_single_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerSearchViewHolder, position: Int) {
        holder.itemView.player_name.text = players[position]?.name ?: ""
        holder.itemView.platform_name.text = KotlinUtils.portalToPlatform(players[position]?.portalID?: "Unknown")
    }
}

class PlayerSearchViewHolder(v: View) : RecyclerView.ViewHolder(v)
