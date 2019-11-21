package com.atruskova.itunesapitesttask.ui


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.atruskova.itunesapitesttask.R
import com.atruskova.itunesapitesttask.data.Resource
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.data.repositories.AlbumRepository
import com.atruskova.itunesapitesttask.viewmodels.SearchActivityViewModel
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel : SearchActivityViewModel
    private lateinit var adapter : SearchListBindingAdapter
    private var searchHandler =  Handler()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchViewModel = ViewModelProviders.of(this).get(SearchActivityViewModel::class.java)
        adapter = SearchListBindingAdapter()
        albums_list_rv.adapter = adapter
        album_list_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                searchHandler.removeCallbacksAndMessages(null)
                searchHandler.postDelayed({
                    searchViewModel.setSearchQuery(newText?:"")
                }, 300)
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.setSearchQuery(query?:"")
                return true
            }
        }
        )
        searchViewModel.setSearchQuery("Moron")
        subscribeAlbumList(searchViewModel.currentSearchList)
    }

    private fun subscribeAlbumList(liveData: LiveData<Resource<List<Album>>>) {
        liveData.observe(this, object : Observer<Resource<List<Album>>> {
            override fun onChanged(t: Resource<List<Album>>?) {
                adapter.submitList(t?.data)
            }
        })
    }
}
