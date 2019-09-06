package com.example.paladict2.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Session (

    @Json(name="session_id")
    var sessionID: String? = null
)