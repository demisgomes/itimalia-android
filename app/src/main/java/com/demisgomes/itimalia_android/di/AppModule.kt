package com.demisgomes.itimalia_android.di

import androidx.room.Room
import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.repository.RepositoryImpl
import com.demisgomes.itimalia_android.retrofit.RetroConfig
import com.demisgomes.itimalia_android.retrofit.WebService
import com.demisgomes.itimalia_android.room.AppDatabase
import com.demisgomes.itimalia_android.room.UserDao
import com.demisgomes.itimalia_android.viewmodel.LoginViewModel
import com.demisgomes.itimalia_android.viewmodel.MainViewModel
import com.demisgomes.itimalia_android.viewmodel.SignUpViewModel
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<WebService> {
        RetroConfig.buildWebService()
    }

    single<Gson> {
        Gson()
    }

    factory<Repository> {
        RepositoryImpl(get(), get())
    }

    viewModel<MainViewModel> {
        MainViewModel(get())
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get(), get())
    }
    viewModel<SignUpViewModel> {
        SignUpViewModel(get())
    }

    single {
        AppDatabase.getInstance(androidContext()).userDao()
    }
}