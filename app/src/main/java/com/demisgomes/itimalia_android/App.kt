package com.demisgomes.itimalia_android

import android.app.Application
import com.demisgomes.itimalia_android.di.setup

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        setup()
    }
}