package com.atruskova.itunesapitesttask.api

import com.atruskova.itunesapitesttask.data.entities.Song
import com.google.gson.annotations.SerializedName

data class LookupSongsApiResponce (
    @SerializedName("resultCount")
    var count: Int =0,
    @SerializedName("results")
    var items: List<Song>?
)