package com.atruskova.itunesapitesttask.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface iTunesApi {

    @Headers("Content-Type: application/json")
    @GET("/search?entity=album")
    fun searchAlbumByQuery(@Query("term") query: String)


}