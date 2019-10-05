package com.example.paladict2.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Player (
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("Level")
    var level: Int? = null,
    @SerializedName("RankedConquest")
    var rankedInfo: RankedStatus? = null,
    @SerializedName("MasteryLevel")
    var masteryLevel: Int? = null,
    @SerializedName("Region")
    var region: String? = null,
    @SerializedName("Platform")
    var platform: String? = null,
    @SerializedName("portal_id")
    var portalID: String? = null,
    @SerializedName("player_id")
    var playerID: String? = null,
    @SerializedName("ActivePlayerId")
    var activePlayerID: String? = null,
    @SerializedName("Wins")
    var wins: String? = null,
    @SerializedName("Losses")
    var losses: String? = null,
    var isChecked: Boolean? = false
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readParcelable(RankedStatus::class.java.classLoader),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(level)
        parcel.writeParcelable(rankedInfo, flags)
        parcel.writeValue(masteryLevel)
        parcel.writeString(region)
        parcel.writeString(platform)
        parcel.writeString(portalID)
        parcel.writeString(playerID)
        parcel.writeString(activePlayerID)
        parcel.writeString(wins)
        parcel.writeString(losses)
        parcel.writeValue(isChecked)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
