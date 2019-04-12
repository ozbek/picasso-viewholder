package com.example.android.recyclerview

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val builder = Picasso.Builder(this)
        builder.downloader(OkHttp3Downloader(this, MAX_SIZE))
        Picasso.setSingletonInstance(builder.build())
    }

    companion object {
        private const val MAX_SIZE = 524288000L // 500 Mb
    }
}
