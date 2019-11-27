package com.atruskova.itunesapitesttask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.atruskova.itunesapitesttask.data.daos.AlbumDao
import com.atruskova.itunesapitesttask.data.daos.SongDao
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.entities.AlbumSearchResult
import com.atruskova.itunesapitesttask.data.entities.Song

@Database(entities = [
    Album::class,
    AlbumSearchResult::class,
    Song::class], version = 1)
@TypeConverters(DatabaseTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao (): AlbumDao
    abstract fun songDao(): SongDao
    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance =
                INSTANCE
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