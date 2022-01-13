package com.demisgomes.itimalia_android.di

import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.repository.RepositoryImpl
import com.demisgomes.itimalia_android.retrofit.RetroConfig
import com.demisgomes.itimalia_android.retrofit.WebService
import com.demisgomes.itimalia_android.viewmodel.LoginViewModel
import com.demisgomes.itimalia_android.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<WebService> {
        RetroConfig.buildWebService()
    }

    factory<Repository> {
        RepositoryImpl(get())
    }

    viewModel<MainViewModel> {
        MainViewModel(get())
    }

    viewModel<LoginViewModel> {
        LoginViewModel(get())
    }
}