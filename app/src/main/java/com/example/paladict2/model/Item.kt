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
    @SerializedName("ItemId")
    var id: Int? = 0,
    @ColumnInfo(name = "item_name")
    @SerializedName("DeviceName")
    var itemName: String? = null,
    @ColumnInfo(name = "description")
    @SerializedName("Description")
    var itemDescription: String? = null,
    @ColumnInfo(name = "price")
    @SerializedName("Price")
    var price: Int? = 0,
    @ColumnInfo(name = "item_icon_url")
    @SerializedName("itemIcon_URL")
    var itemIconURL: String? = null,
    @ColumnInfo(name = "item_type")
    @SerializedName("item_type")
    var itemType: String? = null
) : Serializable {
    constructor(): this(0,"", "", 0, "", "")
}