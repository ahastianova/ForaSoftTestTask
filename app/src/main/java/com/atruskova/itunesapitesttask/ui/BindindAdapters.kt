package com.atruskova.itunesapitesttask.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import com.atruskova.itunesapitesttask.App
import com.atruskova.itunesapitesttask.R
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import kotlinx.android.synthetic.main.signed_textview.view.*

object BindindgAdapters {
    @BindingAdapter("getImage")
    @JvmStatic
    fun getImage(imageView: ImageView, url : String?) {
        Glide.with(App.appContext!!).load(url).centerCrop().into(imageView)
    }

}