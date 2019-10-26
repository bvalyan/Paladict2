package com.example.paladict2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "itemData")
data class Item(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "item_id")
    @SerializedName("id")
    var id: Int? = 0,
    @ColumnInfo(name = "item_name")
    @SerializedName("name")
    var itemName: String? = null
) : Serializable {
    constructor(): this(0,"")
}