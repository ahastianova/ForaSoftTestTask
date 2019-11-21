package com.atruskova.itunesapitesttask.api

import com.atruskova.itunesapitesttask.data.entities.Album
import com.google.gson.annotations.SerializedName

data class SearchApiResponse (
    @SerializedName("resultCount")
    var count: Int =0,
    @SerializedName("results")
    var items: List<Album>?
) {
    var query: String? = ""
}