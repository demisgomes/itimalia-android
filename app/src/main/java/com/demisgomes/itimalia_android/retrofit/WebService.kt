package com.demisgomes.itimalia_android.retrofit

import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {
    @GET("/animals")
    fun getAnimals(): Call<List<Animal>>

    @POST("/login")
    fun login(@Body userLogin: UserLogin): Call<User>
}