package com.atruskova.itunesapitesttask.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.atruskova.itunesapitesttask.R
import com.atruskova.itunesapitesttask.data.Resource
import com.atruskova.itunesapitesttask.data.entities.Song
import com.atruskova.itunesapitesttask.factories.ViewModelsFactory
import com.atruskova.itunesapitesttask.viewmodels.SongListViewModel
import kotlinx.android.synthetic.main.fragment_songs_list.*

class SongsListFragment : Fragment() {
    private var collectId: String? = null
    private lateinit var viewModel : SongListViewModel
    private lateinit var adapter : SongListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            collectId = it.getString(COLLECTION_ID)
        }
        viewModel = ViewModelProviders.of(this,  ViewModelsFactory(collectId)).get(
            SongListViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = SongListAdapter()
        song_list_rv.adapter = adapter
        viewModel.currentList.observe(this, object : Observer<Resource<List<Song>>> {
            override fun onChanged(t: Resource<List<Song>>?) {
               adapter.submitList(t?.data)
            }
        })
    }


    companion object {
        const val COLLECTION_ID = "COLLECTION_ID"
        @JvmStatic
        fun newInstance(collectId: String?) =
            SongsListFragment().apply {
                arguments = Bundle().apply {
                    putString(COLLECTION_ID, collectId)
                }
            }
    }
}
