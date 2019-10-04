package com.example.paladict2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match (
    @SerializedName("ActiveId1")
    var activeID1: String? = null,
    @SerializedName("ActiveId2")
    var activeID2: String? = null,
    @SerializedName("ActiveId3")
    var activeID3: String? = null,
    @SerializedName("ActiveId4")
    var activeID4: String? = null,
    @SerializedName("ActiveLevel1")
    var activeLevel1: String? = null,
    @SerializedName("ActiveLevel2")
    var activeLevel2: String? = null,
    @SerializedName("ActiveLevel3")
    var activeLevel3: String? = null,
    @SerializedName("ActiveLevel4")
    var activeLevel4: String? = null,
    @SerializedName("Active_1")
    var active1: String? = null,
    @SerializedName("Active_2")
    var active2: String? = null,
    @SerializedName("Active_3")
    var active3: String? = null,
    @SerializedName("Active_4")
    var active4: String? = null,
    @SerializedName("Assists")
    var assists: Int? = 0,
    @SerializedName("Champion")
    var champion: String? = null,
    @SerializedName("ChampionId")
    var championID: String? = null,
    @SerializedName("Creeps")
    var minionsKilled: Int? = 0,
    @SerializedName("Damage")
    var damage: Int? = 0,
    @SerializedName("Damage_Bot")
    var damageBot: Int? = 0,
    @SerializedName("Damage_Done_In_Hand")
    var damageInHand: Int? = 0,
    @SerializedName("Damage_Mitigated")
    var damageMitigated: Int? = 0,
    @SerializedName("Damage_Structure")
    var structureDamage: Int? = 0,
    @SerializedName("Damage_Taken")
    var damageTaken: Int? = 0,
    @SerializedName("Damage_Taken_Magical")
    var magicalDamageTaken: Int? = 0,
    @SerializedName("Damage_Taken_Physical")
    var physicalDamageTaken: Int? = 0,
    @SerializedName("Deaths")
    var deaths : Int? = 0,
    @SerializedName("Distance_Traveled")
    var distanceTraveled : Int? = 0,
    @SerializedName("Gold")
    var gold : Int? = 0,
    @SerializedName("Healing")
    var healing : Int? = 0,
    @SerializedName("Healing_Bot")
    var healingBot : Int? = 0,
    @SerializedName("Healing_Player_Self")
    var selfHealing : Int? = 0,
    @SerializedName("ItemId1")
    var itemID1 : String? = null,
    @SerializedName("ItemId2")
    var itemID2 : String? = null,
    @SerializedName("ItemId3")
    var itemID3 : String? = null,
    @SerializedName("ItemId4")
    var itemID4 : String? = null,
    @SerializedName("ItemId5")
    var itemID5 : String? = null,
    @SerializedName("ItemId6")
    var itemID6 : String? = null,
    @SerializedName("ItemLevel1")
    var itemLevel1 : String? = null,
    @SerializedName("ItemLevel2")
    var itemLevel2 : String? = null,
    @SerializedName("ItemLevel3")
    var itemLevel3 : String? = null,
    @SerializedName("ItemLevel4")
    var itemLevel4 : String? = null,
    @SerializedName("ItemLevel5")
    var itemLevel5 : String? = null,
    @SerializedName("ItemLevel6")
    var itemLevel6 : String? = null,
    @SerializedName("Item_1")
    var item1 : String? = null,
    @SerializedName("Item_2")
    var item2 : String? = null,
    @SerializedName("Item_3")
    var item3 : String? = null,
    @SerializedName("Item_4")
    var item4 : String? = null,
    @SerializedName("Item_5")
    var item5 : String? = null,
    @SerializedName("Item_6")
    var item6 : String? = null,
    @SerializedName("Killing_Spree")
    var killingSpree : Int? = 0,
    @SerializedName("Kills")
    var kills : Int? = 0,
    @SerializedName("Level")
    var level : Int? = 0,
    @SerializedName("Map_Game")
    var map : String? = null,
    @SerializedName("Match")
    var matchID : Int? = 0,
    @SerializedName("Match_Queue_Id")
    var matchQueueID : String? = null,
    @SerializedName("Match_Time")
    var matchTime : String? = null,
    @SerializedName("Minutes")
    var minutes : Int? = 0,
    @SerializedName("Multi_kill_Max")
    var multiKillsMax : Int? = 0,
    @SerializedName("Objective_Assists")
    var obbjectiveAssists : Int? = 0,
    @SerializedName("Queue")
    var queue : String? = null,
    @SerializedName("Region")
    var region : String? = null,
    @SerializedName("Skin")
    var skin : String? = null,
    @SerializedName("SkinId")
    var skinID : Int? = 0,
    @SerializedName("Surrendered")
    var matchesSurrendered : Int? = 0,
    @SerializedName("TaskForce")
    var team : String? = null,
    @SerializedName("Team1Score")
    var teamOneScore : Int? = 0,
    @SerializedName("Team2Score")
    var teamTwoScore : Int? = 0,
    @SerializedName("Time_In_Match_Seconds")
    var timeInMatch : Int? = 0,
    @SerializedName("Wards_Placed")
    var wardsPlaced : Int? = 0,
    @SerializedName("Win_Status")
    var winStatus : String? = null,
    @SerializedName("Winning_TaskForce")
    var winningTeam : String? = null,
    @SerializedName("playerId")
    var playerID : String? = null,
    @SerializedName("playerName")
    var playerName : String? = null,
    @SerializedName("ret_msg")
    var apiMessage : String? = null
    ) : Serializable