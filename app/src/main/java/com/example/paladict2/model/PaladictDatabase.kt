package com.example.paladict2.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Champion::class, Item::class], version = 1)
@TypeConverters(Converters::class)
abstract class PaladictDatabase : RoomDatabase() {

    abstract fun championDao(): ChampionDao
    abstract fun itemDao() : ItemDao

    companion object {

        @Volatile
        private var INSTANCE: PaladictDatabase? = null

        fun getInstance(context: Context): PaladictDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PaladictDatabase::class.java, "paladict.db"
                )
                    .build()
                INSTANCE = instance
                return instance
            }

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}