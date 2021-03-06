package com.meazza.arcade.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(mainModule, authModule, ticTacToeModule, duckHuntModule))
        }
    }
}