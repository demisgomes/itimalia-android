package com.demisgomes.itimalia_android.retrofit

import com.demisgomes.itimalia_android.domain.animal.Animal
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("/animals")
    fun getAnimals(): Call<List<Animal>>
}