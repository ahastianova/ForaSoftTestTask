package com.atruskova.itunesapitesttask.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val LOG_TAG = "NetworkService"
class iTunesService (baseUrl: String) {

    private var mRetrofit: Retrofit? = null

    init {
        try {

            mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        } catch (e: Exception) {
            Log.e(LOG_TAG, e.stackTrace.toString())
        }

    }

    fun getApi(): iTunesApi? {
        return mRetrofit?.create(iTunesApi::class.java)
    }

}