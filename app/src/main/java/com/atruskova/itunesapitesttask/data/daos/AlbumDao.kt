package com.atruskova.itunesapitesttask.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.entities.AlbumSearchResult

@Dao
interface AlbumDao {

    @Query("SELECT * FROM AlbumSearchResult WHERE `query` = :query")
    fun getSearchResult(query: String) : LiveData<AlbumSearchResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchResult(searchResult: AlbumSearchResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(albums: List<Album>?)

    @Query("SELECT * FROM Album WHERE ID in (:albumIds)")
    fun getAlbums(albumIds: List<String>?): LiveData<List<Album>>

    @Query("SELECT * FROM Album WHERE ID=:albmId")
    fun getAlbumById(albmId: String?): LiveData<Album>


}