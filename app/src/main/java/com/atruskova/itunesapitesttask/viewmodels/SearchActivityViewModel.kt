package com.atruskova.itunesapitesttask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.atruskova.itunesapitesttask.data.AbsentLiveData
import com.atruskova.itunesapitesttask.data.Resource
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.repositories.AlbumRepository

class SearchActivityViewModel: ViewModel() {
    var albumRepository = AlbumRepository()
    var searchQuery: MutableLiveData<String> = MutableLiveData()

    var currentSearchList: LiveData<Resource<List<Album>>> = Transformations.switchMap(searchQuery, { query ->
        if (query.isNullOrEmpty())
            AbsentLiveData.create()
        else
            albumRepository.search(query)
    })

    fun setSearchQuery(query: String) {
        searchQuery.postValue(query)
    }
}