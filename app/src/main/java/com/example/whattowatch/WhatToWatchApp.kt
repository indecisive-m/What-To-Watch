package com.example.whattowatch

import android.app.Application
import com.example.whattowatch.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WhatToWatchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WhatToWatchApp)
            modules(koinModule)
        }
    }
}

