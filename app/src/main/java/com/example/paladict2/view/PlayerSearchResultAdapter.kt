package com.example.paladict2.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Player

class PlayerSearchResultAdapter(private var players: List<Player>) :

    RecyclerView.Adapter<PlayerSearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.player_search_result_single)
        return PlayerSearchResultAdapter.ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSearchResult(players[position])
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindSearchResult(
            player: Player
        ) {
           
        }

    }


}
