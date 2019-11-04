package com.example.paladict2.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ItemDao {
    @Query("Select * FROM itemData ORDER BY item_name" )
    fun getAllItems() : LiveData<List<Item>>

    @Query("SELECT * FROM itemData WHERE item_id = :id")
    fun getItemByID(id : Int) : LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Query("DELETE FROM itemData")
    fun deleteAll()
}
