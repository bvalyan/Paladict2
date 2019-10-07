package com.example.paladict2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Champion::class], version = 1)
abstract class PaladictDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao

    companion object{
        private var INSTANCE: PaladictDatabase? = null

        fun getInstance(context: Context) : PaladictDatabase?{
            if(INSTANCE == null){
                synchronized(PaladictDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PaladictDatabase::class.java, "paladict.db")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}