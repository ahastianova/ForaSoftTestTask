package com.atruskova.itunesapitesttask.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["query"])
data class AlbumSearchResult (
        val query: String,
        val albumIds: List<String>?,
        val totalCount: Int
    )
