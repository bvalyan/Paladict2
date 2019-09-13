package com.example.paladict2.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import com.example.paladict2.model.Ability
import com.example.paladict2.model.Champion
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class ChampionViewModel(var champion: Champion) : ViewModel(){
    var name = champion.name
    var role = champion.roles
    var health = champion.health
    var iconURL = champion.iconURL
    var speed = champion.speed
    var onRotation = champion.isOnFreeRotation
    var title = champion.title
    var ability1 = champion.ability1
    var ability2 = champion.ability2
    var ability3 = champion.ability3
    var ability4 = champion.ability4
    var ability5 = champion.ability5
    var latestChampion = champion.latestChampion

    fun getRotationText(): String {
        return if(onRotation!!.toBoolean()){
            "FREE"
        } else {
            "PAID"
        }
    }

    object DataBindingAdapter {
        @BindingAdapter("bind:imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageURL: String) {
            Picasso.get()
                .load(imageURL)
                .into(view)
        }
    }




}