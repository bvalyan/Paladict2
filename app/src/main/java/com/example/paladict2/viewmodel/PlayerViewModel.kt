package com.example.paladict2.viewmodel

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import androidx.databinding.adapters.Converters.convertColorToDrawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.paladict2.model.MergedPlayerSearchData
import com.example.paladict2.model.Player
import com.example.paladict2.viewmodel.repositories.PlayerRepository

class PlayerViewModel : ViewModel(){

    //TODO: Cleanup hard coded strings in this class!

    private val playerRepository = PlayerRepository()

    val combinedPlayerSearchData = MutableLiveData<MergedPlayerSearchData>()

    val player: LiveData<Player> = Transformations.switchMap(combinedPlayerSearchData) {
        playerRepository.getMutableLiveData(it.session, it.playerID)
    }

    val rankedBackground: LiveData<Int> = Transformations.map(player){
            tierToBackground(player)
    }

    val tierText : LiveData<String> = Transformations.map(player){
        tierToString(player)
    }

    val toNextTier : LiveData<String> = Transformations.map(player){
        retrieveTierProgress(player)
    }

    val level : LiveData<String> = Transformations.map(player){
        player.value!!.level.toString()
    }

    val masteryLevel : LiveData<String> = Transformations.map(player){
        player.value!!.masteryLevel.toString()
    }

    private fun retrieveTierProgress(player: LiveData<Player>): String? {
        return if(player.value!!.rankedInfo!!.points!! < 100 || player.value!!.rankedInfo!!.tier!! <= 25){
            player.value!!.rankedInfo!!.points!!.toString() + "/100"
        } else{
            ""
        }
    }

    private fun tierToString(player: LiveData<Player>): String? {
        when(player.value!!.rankedInfo!!.tier){
            0 -> return "Unranked"
            1 -> return "Bronze V"
            2 -> return "Bronze IV"
            3 -> return "Bronze III"
            4 -> return "Bronze II"
            5 -> return "Bronze I"
            6 -> return "Silver V"
            7 -> return "Silver IV"
            8 -> return "Silver III"
            9 -> return "Silver II"
            10 -> return "Silver I"
            11 -> return "Gold V"
            12 -> return "Gold IV"
            13 -> return "Gold III"
            14 -> return "Gold II"
            15 -> return "Gold I"
            16 -> return "Platinum V"
            17 -> return "Platinum IV"
            18 -> return "Platinum III"
            19 -> return "Platinum II"
            20 -> return "Platinum I"
            21 -> return "Diamond V"
            22 -> return "Diamond IV"
            23 -> return "Diamond III"
            24 -> return "Diamond II"
            25 -> return "Diamond I"
            26 -> return "Master"
            27 -> return "Grandmaster"
        }
        return "Unknown"
    }

    override fun onCleared() {
        super.onCleared()
        playerRepository.completableJob.cancel()
    }

    private fun tierToBackground(player: LiveData<Player>): Int {
        when(player.value!!.rankedInfo!!.tier){
            0 -> return Color.parseColor("#1c2e2e")
            in 1..5 -> return Color.parseColor("#132e0d")
            in 6..10 -> return Color.parseColor("#0a1429")
            in 10..15 -> return Color.parseColor("#470004")
            in 16..20 -> return Color.parseColor("#1a0426")
            in 21..25 -> return Color.parseColor("#00192e")
            26 -> return Color.parseColor("#183b30")
            27 -> return Color.parseColor("#36341e")
        }
        return Color.DKGRAY
    }

    private fun masterGradient(): GradientDrawable {
        var colorArray = intArrayOf(Color.parseColor("#1c093b"), Color.parseColor("#4a4500"))
        return GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, colorArray)
    }
}