package com.example.paladict2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item (
    @SerializedName("id")
    var id : Int? = 0
) : Serializable