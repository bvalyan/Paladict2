package com.example.paladict2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "championData")
data class Champion(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    @ColumnInfo(name = "champion_id")
    @SerializedName("id")
    var championID : String? = null,
    @ColumnInfo(name = "health")
    @SerializedName("Health")
    var health: String? = null,
    @ColumnInfo(name = "lore")
    @SerializedName("Lore")
    var lore: String? = null,
    @ColumnInfo(name = "name")
    @SerializedName("Name")
    var name: String? = null,
    @ColumnInfo(name = "title")
    @SerializedName("Title")
    var title: String? = null,
    @ColumnInfo(name = "champion_icon_url")
    @SerializedName("ChampionIcon_URL")
    var iconURL: String? = null,
    @ColumnInfo(name = "on_free_rotation")
    @SerializedName("OnFreeWeeklyRotation")
    var isOnFreeRotation: String? = null,
    @ColumnInfo(name = "roles")
    @SerializedName("Roles")
    var roles: String? = null,
    @ColumnInfo(name = "speed")
    @SerializedName("Speed")
    var speed: String? = null,
    @ColumnInfo(name = "ability_1")
    @SerializedName("Ability_1")
    var ability1: Ability? = null,
    @ColumnInfo(name = "ability_2")
    @SerializedName("Ability_2")
    var ability2: Ability? = null,
    @ColumnInfo(name = "ability_3")
    @SerializedName("Ability_3")
    var ability3: Ability? = null,
    @ColumnInfo(name = "ability_4")
    @SerializedName("Ability_4")
    var ability4: Ability? = null,
    @ColumnInfo(name = "ability_5")
    @SerializedName("Ability_5")
    var ability5: Ability? = null,
    @ColumnInfo(name = "latest_champion")
    @SerializedName("latestChampion")
    var latestChampion: String? = null
) {
    constructor():this(null,"","","","","","","","","",null,null,null,null,null,"")
}