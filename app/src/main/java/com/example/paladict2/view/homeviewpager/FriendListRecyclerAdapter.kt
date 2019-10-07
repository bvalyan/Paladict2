package com.example.paladict2.view.homeviewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Ability
import com.example.paladict2.model.Player
import com.example.paladict2.view.BindableAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ability_list_item.view.*
import kotlinx.android.synthetic.main.friend_list_single.view.*

class FriendListRecyclerAdapter(private var players: List<Player>) : RecyclerView.Adapter<FriendListRecyclerAdapter.PlayerHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PlayerHolder(inflater.inflate(R.layout.friend_list_single, parent, false))
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bind(players[position])
    }


    class PlayerHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(player: Player){
            itemView.name_text_view.text = player.name
            itemView.player_platform.text = player.platform
        }

    }
}