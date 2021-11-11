package com.demisgomes.itimalia_android.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroConfig {
    private fun buildRetrofit() =
        Retrofit.Builder()
            .baseUrl("https://afternoon-caverns-61373.herokuapp.com")
            .addConverterFactory(
                GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
            .build()

    fun buildWebService() = buildRetrofit().create(WebService::class.java)
}