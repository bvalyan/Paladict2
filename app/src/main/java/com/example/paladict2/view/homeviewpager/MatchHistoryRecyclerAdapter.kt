package com.example.paladict2.view.homeviewpager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.R
import com.example.paladict2.model.Champion
import com.example.paladict2.model.Match
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.match_history_single.view.*

class MatchHistoryRecyclerAdapter(private var matches: List<Match>?, private var champions: List<Champion>) :
    RecyclerView.Adapter<MatchHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MatchHolder(
            inflater.inflate(
                R.layout.match_history_single,
                parent,
                false
            ),
            champions
        )
    }

    override fun getItemCount(): Int {
        return matches!!.size
    }

    override fun onBindViewHolder(holder: MatchHolder, position: Int) {
        holder.bind(matches!![position])
    }

}

class MatchHolder(itemView: View, var champions: List<Champion>) : RecyclerView.ViewHolder(itemView) {

    fun bind(match : Match){
        val usedChampion = getChampion(match)
        itemView.hist_champion_name.text = usedChampion.name
        itemView.hist_win_status.text = match.winStatus
        itemView.hisotry_kda.text = generateMatchKdaString(match)
        if(match.winStatus!!.toLowerCase() == "win"){
            itemView.hist_win_status.setTextColor(Color.GREEN)
        } else {
            itemView.hist_win_status.setTextColor(Color.RED)
        }

        Picasso.get().load(usedChampion.iconURL).into(itemView.champion_history_image)
    }

    private fun generateMatchKdaString(match: Match): CharSequence? {
        return match.kills.toString() + "/" + match.deaths.toString() + "/" + match.assists.toString()
    }

    private fun getChampion(match: Match): Champion {
        for(champion in champions){
            if(champion.championID == match.championID){
                return champion
            }
        }
        return Champion()
    }
}
