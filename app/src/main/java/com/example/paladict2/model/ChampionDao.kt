package com.example.paladict2.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ChampionDao {

    @Query("Select * FROM championData ORDER BY name" )
    fun getAllChampions() :  LiveData<List<Champion>>

    @Query("SELECT * FROM championData WHERE champion_id = :id")
    fun getChampionByID(id : Int) : LiveData<Champion>

    @Insert(onConflict = REPLACE)
    fun insert(championData: Champion)

    @Insert(onConflict = REPLACE)
    fun insertAll(championData: List<Champion>)

    @Query("DELETE FROM championData")
    fun deleteAll()

}