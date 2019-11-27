package com.atruskova.itunesapitesttask.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.atruskova.itunesapitesttask.R
import com.atruskova.itunesapitesttask.data.entities.Song
import com.atruskova.itunesapitesttask.databinding.SongListItemBinding

class SongListAdapter : ListBindingAdapter<Song, SongListItemBinding> (
    diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.ID == newItem.ID
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.equals(newItem)
        }
    }
){
    override fun createBinding(parent: ViewGroup): SongListItemBinding {
        return DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.song_list_item,
                parent,
                false
            )
    }

    override fun bind(binding: SongListItemBinding, item: Song) {
        binding.song = item
    }
}