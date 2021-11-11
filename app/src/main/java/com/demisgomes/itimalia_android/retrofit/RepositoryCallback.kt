package com.demisgomes.itimalia_android.retrofit

interface RepositoryCallback<T>{
    fun success(t: T)
    fun failure(message: String)
}