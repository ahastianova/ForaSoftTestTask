package com.atruskova.itunesapitesttask.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import com.atruskova.itunesapitesttask.R

class AlbumListBindingHandler {
    fun onClick(view: View, albumId: String) {
        var bundle = Bundle()
        bundle.putString(AboutAlbumFragment.COLLECTION_ID, albumId)
        Navigation.findNavController(view).navigate(R.id.aboutAlbumFragment, bundle)
    }
}