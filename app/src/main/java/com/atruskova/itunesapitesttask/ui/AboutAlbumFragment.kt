package com.atruskova.itunesapitesttask.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.atruskova.itunesapitesttask.R

import com.atruskova.itunesapitesttask.databinding.FragmentAboutAlbumBinding
import com.atruskova.itunesapitesttask.factories.ViewModelsFactory
import com.atruskova.itunesapitesttask.viewmodels.AboutFragmentViewModel
import kotlinx.android.synthetic.main.fragment_about_album.*


class AboutAlbumFragment : Fragment() {
    private var albumId: String? = null
    private lateinit var binding: FragmentAboutAlbumBinding
    private lateinit var songListFragment: SongsListFragment
    private lateinit var aboutAlbumViewModel: AboutFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getString(COLLECTION_ID)
        }
        songListFragment = SongsListFragment.newInstance(albumId)
        fragmentManager!!.beginTransaction()
            .add(R.id.about_song_list_container, songListFragment).commit()
        aboutAlbumViewModel = ViewModelProviders.of(this,  ViewModelsFactory(albumId)).get(AboutFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(
            inflater, R.layout.fragment_about_album, container, false)
        binding.viewModel = aboutAlbumViewModel
        binding.lifecycleOwner = this.activity
        return binding.root
    }



    companion object {
        const val COLLECTION_ID = "noteId"
    }
}
