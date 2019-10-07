package com.example.paladict2.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject

class Converters {

    @TypeConverter
    fun abilityToString(ability: Ability) : String{
        return Gson().toJson(ability)
    }

    @TypeConverter
    fun stringToAbility(string: String) : Ability{
        return Gson().fromJson(string, Ability::class.java)
    }
}