package com.example.paladict2.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RankedStatus (
    @SerializedName("Tier")
    var tier : Int? = 0,
    @SerializedName("Points")
    var points : Int? = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(tier)
        parcel.writeValue(points)
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
