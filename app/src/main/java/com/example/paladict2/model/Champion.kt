package com.example.paladict2.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Champion(

    @Json(name = "Health")
    var health: String? = null,
    @Json(name = "Lore")
    var lore: String? = null,
    @Json(name = "Name")
    var name: String? = null,
    @Json(name = "ChampionIcon_URL")
    var iconURL: String? = null,
    @Json(name = "OnFreeRotation")
    var isOnFreeRotation: String? = null,
    @Json(name = "Roles")
    var roles: String? = null
)