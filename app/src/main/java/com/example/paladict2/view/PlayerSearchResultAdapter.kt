package com.example.paladict2.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Player
import com.example.paladict2.utils.KotlinUtils.Companion.portalToPlatform
import com.google.android.material.radiobutton.MaterialRadioButton
import kotlinx.android.synthetic.main.player_search_result_single.view.*

class PlayerSearchResultAdapter(
    private var players: List<Player?>,
    private val radioButtonClick: (Any?) -> Unit
) :

    RecyclerView.Adapter<PlayerSearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.player_search_result_single)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindSearchResult(players[position], radioButtonClick)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v

        fun bindSearchResult(
            player: Player?,
            radioButtonClick: (Any?) -> Unit
        ) {
            view.name_text_view.text = player?.name
            view.player_platform.text = portalToPlatform(player?.portalID)
            view.select_player_btn.setOnClickListener {
                radioButtonClick(player)
            }
        }
    }


}
