package com.atruskova.itunesapitesttask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.atruskova.itunesapitesttask.R
import com.atruskova.itunesapitesttask.data.entities.Album
import com.atruskova.itunesapitesttask.databinding.AlbumListItemBinding

class SearchListBindingAdapter : ListBindingAdapter<Album, AlbumListItemBinding > (
    diffCallback = object : DiffUtil.ItemCallback<Album> (){
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.ID==newItem.ID
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.equals(newItem)
        }
    }
){

    override fun createBinding(parent: ViewGroup): AlbumListItemBinding {
        return  DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.album_list_item,
                parent,
                false
            )
    }

    override fun bind(binding: AlbumListItemBinding, item: Album) {
        binding.album = item
    }

}