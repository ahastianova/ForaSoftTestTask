package com.atruskova.itunesapitesttask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.atruskova.itunesapitesttask.data.Resource
import com.atruskova.itunesapitesttask.data.entities.Song
import com.atruskova.itunesapitesttask.data.repositories.AlbumRepository

class SongListViewModel(collectionId: String?) : ViewModel() {
    private var albmRepository = AlbumRepository()
    var currentList: LiveData<Resource<List<Song>>>
    init {
        currentList = albmRepository.getAlbumSongs(collectionId?:"")
    }
}