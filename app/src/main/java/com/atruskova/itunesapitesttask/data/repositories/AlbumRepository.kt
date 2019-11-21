package com.atruskova.itunesapitesttask.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.atruskova.itunesapitesttask.App
import com.atruskova.itunesapitesttask.AppExecutors
import com.atruskova.itunesapitesttask.api.SearchApiResponse
import com.atruskova.itunesapitesttask.api.iTunesService
import com.atruskova.itunesapitesttask.data.*
import com.atruskova.itunesapitesttask.data.database.AppDatabase
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.entities.AlbumSearchResult
import retrofit2.Call

class AlbumRepository

{
    private val db: AppDatabase? = App.instance?.getDataBase()
    private val exucutors: AppExecutors = App.executors
    private val iTunesService: iTunesService? = App.iTunesService
    fun search (query: String): LiveData<Resource<List<Album>>>{
        return object : NetworkBoundResource<SearchApiResponse, List<Album>>(exucutors) {
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

            override fun createCall(): Call<SearchApiResponse> = if (query.isNullOrEmpty())
                iTunesService!!.getApi()!!.getPopularAlbums()
            else
                iTunesService!!.getApi()!!.search(query)

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

}