package com.andreygolovin.stopwatchapp

import android.app.Application
import com.andreygolovin.stopwatchapp.di.mainViewModel
import org.koin.core.context.startKoin

class StopwatchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(mainViewModel)
        }
    }
}