package com.atruskova.itunesapitesttask.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.atruskova.itunesapitesttask.App
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions

object BindindgAdapters {
    @BindingAdapter("getImage")
    @JvmStatic
    fun getImage(imageView: ImageView, url : String) {
        Glide.with(App.appContext!!).load(url).centerCrop().into(imageView)
    }

}