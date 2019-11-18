package com.atruskova.itunesapitesttask.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atruskova.itunesapitesttask.data.entities.Album

@Database(entities = [Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "App_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}