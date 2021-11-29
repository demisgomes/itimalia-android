package com.demisgomes.itimalia_android.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

fun Application.setup() {
    startKoin {
        androidContext(this@setup)
        androidLogger(Level.ERROR)
        modules(listOf(appModule))
    }
}