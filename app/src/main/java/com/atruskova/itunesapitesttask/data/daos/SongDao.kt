package com.atruskova.itunesapitesttask.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.entities.Song

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSongs(songs: List<Song>?)

    @Query("SELECT * FROM Song WHERE CollectionId=:collectionId")
    fun getSongsForCollection(collectionId: String?): LiveData<List<Song>>
}