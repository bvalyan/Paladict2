package com.example.paladict2.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RankedStatus (
    @SerializedName("Tier")
    var tier : String? = null,
    @SerializedName("Points")
    var points : String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(tier)
        parcel.writeString(points)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RankedStatus> {
        override fun createFromParcel(parcel: Parcel): RankedStatus {
            return RankedStatus(parcel)
        }

        override fun newArray(size: Int): Array<RankedStatus?> {
            return arrayOfNulls(size)
        }
    }
}
