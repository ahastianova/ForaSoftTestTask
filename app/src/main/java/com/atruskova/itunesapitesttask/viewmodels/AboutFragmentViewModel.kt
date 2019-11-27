package com.atruskova.itunesapitesttask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.repositories.AlbumRepository

class AboutFragmentViewModel(albumId: String?): ViewModel() {
    private var albmRepository = AlbumRepository()
    var currentAlbum: LiveData<Album>?
    init {
        currentAlbum = albmRepository.getAlbum(albumId?:"")
    }

}