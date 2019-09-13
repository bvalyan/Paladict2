package com.example.paladict2.model

import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName( "Id")  val id: String?,
    @SerializedName( "Summary")  val summary: String?,
    @SerializedName( "Description") var description: String?,
    @SerializedName("URL")  val iconImageURL: String?
)
