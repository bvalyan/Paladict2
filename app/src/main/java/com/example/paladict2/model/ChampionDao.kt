package com.example.paladict2.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ChampionDao {

    @Query("Select * FROM championData")
    fun getAllChampions() :  LiveData<List<Champion>>

    @Query("SELECT * FROM championData WHERE id = :id")
    fun getItemByID(id : String)

    @Insert(onConflict = REPLACE)
    fun insert(championData: Champion)
}