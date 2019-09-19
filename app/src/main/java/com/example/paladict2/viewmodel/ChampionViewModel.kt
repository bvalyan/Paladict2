package com.example.paladict2.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.paladict2.model.Ability
import com.example.paladict2.model.Champion
import com.example.paladict2.view.BindableAdapter
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
    var lore = champion.lore

    fun getRotationText(): String {
        return if(onRotation!!.toBoolean()){
            "FREE"
        } else {
            "PAID"
        }
    }

    fun getAbilities() : List<Ability> {
        val abilityList = arrayListOf<Ability>()
        abilityList.add(ability1!!)
        abilityList.add(ability2!!)
        abilityList.add(ability3!!)
        abilityList.add(ability4!!)
        abilityList.add(ability5!!)

        return abilityList
    }

    object DataBindingAdapter {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view: ImageView, imageURL: String) {
            Picasso.get()
                .load(imageURL)
                .into(view)
        }

        @BindingAdapter("data")
        @JvmStatic
        fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
            if(recyclerView.adapter is BindableAdapter<*>) {
                (recyclerView.adapter as BindableAdapter<T>).setData(data)
            }
        }
    }




}