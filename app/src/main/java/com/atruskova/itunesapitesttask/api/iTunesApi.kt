package com.atruskova.itunesapitesttask.api

import androidx.lifecycle.LiveData
import com.atruskova.itunesapitesttask.data.ApiResponse
import com.atruskova.itunesapitesttask.data.entities.AlbumSearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface iTunesApi {

    @Headers("Content-Type: application/json")
    @GET("/search?entity=album")
    fun search(@Query("term") query: String) : Call<SearchApiResponse>

    @Headers("Content-Type: application/json")
    @GET("/search?entity=album")
    fun getPopularAlbums() : Call<SearchApiResponse>

}