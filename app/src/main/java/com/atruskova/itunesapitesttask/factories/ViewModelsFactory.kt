package com.atruskova.itunesapitesttask.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atruskova.itunesapitesttask.viewmodels.AboutFragmentViewModel
import com.atruskova.itunesapitesttask.viewmodels.SongListViewModel

class ViewModelsFactory  (id: String?) : ViewModelProvider.NewInstanceFactory() {
    private var _id: String?
    init {
        _id = id
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass==AboutFragmentViewModel::class.java)
            return AboutFragmentViewModel(_id) as T
        if (modelClass==SongListViewModel::class.java)
            return SongListViewModel(_id) as T
        return null as T
    }
}