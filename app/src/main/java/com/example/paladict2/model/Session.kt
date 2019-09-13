package com.example.paladict2.model


import com.google.gson.annotations.SerializedName

data class Session (

    @SerializedName("session_id")
    var sessionID: String? = null
)