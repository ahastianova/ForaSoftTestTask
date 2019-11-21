package com.atruskova.itunesapitesttask

import android.app.Application
import android.content.Context
import com.atruskova.itunesapitesttask.api.iTunesService
import com.atruskova.itunesapitesttask.data.database.AppDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
        iTunesService = iTunesService("https://itunes.apple.com/")
        instance = this
    }
    fun getDataBase(): AppDatabase {
        return AppDatabase.getDatabase(this)
    }
    companion object {
        var instance: App? = null
            private set
        var executors = AppExecutors()

        var iTunesService: iTunesService? = null
            private set

        var appContext: Context? = null
            private set
    }
}