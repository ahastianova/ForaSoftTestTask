package com.atruskova.itunesapitesttask.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.atruskova.itunesapitesttask.App
import com.atruskova.itunesapitesttask.AppExecutors
import com.atruskova.itunesapitesttask.api.LookupSongsApiResponce
import com.atruskova.itunesapitesttask.api.SearchApiResponse
import com.atruskova.itunesapitesttask.api.WrapperTypes
import com.atruskova.itunesapitesttask.api.iTunesService
import com.atruskova.itunesapitesttask.data.*
import com.atruskova.itunesapitesttask.data.database.AppDatabase
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.entities.AlbumSearchResult
import com.atruskova.itunesapitesttask.data.entities.Song
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call

class AlbumRepository

{
    private val db: AppDatabase? = App.instance?.getDataBase()
    private val executors: AppExecutors = App.executors
    private val iTunesService: iTunesService? = App.iTunesService
    fun search (query: String): LiveData<Resource<List<Album>>>{
        return object : NetworkBoundResource<SearchApiResponse, List<Album>>(executors) {
            override fun saveCallResult(item: SearchApiResponse) {
                var albumsIds = item.items?.map { it -> it.ID }
                var searchResult = AlbumSearchResult(
                    query = query,
                    albumIds = albumsIds,
                    totalCount = item.count
                )
                db!!.runInTransaction {
                    db.albumDao().insertSearchResult(searchResult)
                    db.albumDao().insertAlbums(item.items)
                }
            }

            override fun createCall(): Call<SearchApiResponse> = iTunesService!!.getApi()!!.search(query)

            override fun loadFromDb(): LiveData<List<Album>> {
                return Transformations.switchMap(db!!.albumDao().getSearchResult(query)) { searchData ->
                    if (searchData == null) {
                        AbsentLiveData.create()
                    } else {
                        db.albumDao().getAlbums(searchData.albumIds)
                    }
                }
            }

            override fun shouldFetch(data: List<Album>?): Boolean {
                return data == null || query.isNullOrEmpty()
            }
        }.asLiveData()


    }

    fun getAlbum(albmId: String?) : LiveData<Album>? = db?.albumDao()?.getAlbumById(albmId)

    fun getAlbumSongs(albmId: String?) : LiveData<Resource<List<Song>>> {
        return object : NetworkBoundResource<LookupSongsApiResponce, List<Song>>(executors) {
            override fun saveCallResult(item: LookupSongsApiResponce) {
                db?.songDao()?.insertSongs(item.items?.filter {
                    it.WrapperType == WrapperTypes.TRACK
                })
            }

            override fun shouldFetch(data: List<Song>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun loadFromDb(): LiveData<List<Song>>  = db!!.songDao().getSongsForCollection(albmId)

            override fun createCall(): Call<LookupSongsApiResponce> = iTunesService?.getApi()!!.getCollectionsSongs(albmId)
        }.asLiveData()
    }


}