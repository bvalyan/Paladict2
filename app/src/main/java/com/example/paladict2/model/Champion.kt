package com.example.paladict2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Champion(
    @SerializedName("Health")
    var health: String? = null,
    @SerializedName("Lore")
    var lore: String? = null,
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("Title")
    var title: String? = null,
    @SerializedName("ChampionIcon_URL")
    var iconURL: String? = null,
    @SerializedName("OnFreeWeeklyRotation")
    var isOnFreeRotation: String? = null,
    @SerializedName("Roles")
    var roles: String? = null,
    @SerializedName("Speed")
    var speed: String? = null,
    @SerializedName("Ability_1")
    var ability1: Ability? = null,
    @SerializedName("Ability_2")
    var ability2: Ability? = null,
    @SerializedName("Ability_3")
    var ability3: Ability? = null,
    @SerializedName("Ability_4")
    var ability4: Ability? = null,
    @SerializedName("Ability_5")
    var ability5: Ability? = null,
    @SerializedName("latestChampion")
    var latestChampion: String? = null
) : Serializable