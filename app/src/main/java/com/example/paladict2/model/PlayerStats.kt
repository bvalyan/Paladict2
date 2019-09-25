package com.example.paladict2.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PlayerStats (
    @SerializedName("Assists")
    var assists : Int? = null,
    @SerializedName("Champion")
    var champion : String? = null,
    @SerializedName("ChampionId")
    var championID : Int? = null,
    @SerializedName("Deaths")
    var deaths : Int? = null,
    @SerializedName("Gold")
    var gold : Int? = null,
    @SerializedName("Kills")
    var kills : Int? = null,
    @SerializedName("LastPlayed")
    var lastPLayed : String? = null,
    @SerializedName("Losses")
    var losses : Int? = null,
    @SerializedName("Matches")
    var matches : Int? = null,
    @SerializedName("Minutes")
    var minutes : Int? = null,
    @SerializedName("Wins")
    var wins : Int? = null,
    @SerializedName("player_id")
    var playerID : Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(assists)
        parcel.writeString(champion)
        parcel.writeValue(championID)
        parcel.writeValue(deaths)
        parcel.writeValue(gold)
        parcel.writeValue(kills)
        parcel.writeString(lastPLayed)
        parcel.writeValue(losses)
        parcel.writeValue(matches)
        parcel.writeValue(minutes)
        parcel.writeValue(wins)
        parcel.writeValue(playerID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerStats> {
        override fun createFromParcel(parcel: Parcel): PlayerStats {
            return PlayerStats(parcel)
        }

        override fun newArray(size: Int): Array<PlayerStats?> {
            return arrayOfNulls(size)
        }
    }
}