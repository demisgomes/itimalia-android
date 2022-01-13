package com.demisgomes.itimalia_android.repository

import android.util.Log
import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import com.demisgomes.itimalia_android.retrofit.WebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(private val webService: WebService) : Repository {
    override fun getAnimalsList(callbackAnimals: RepositoryCallback<List<Animal>>) {
        val call = webService.getAnimals()

        call.enqueue(object : Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                if (response.isSuccessful) {
                    response.body()?.let { callbackAnimals.success(it) }
                }
                else callbackAnimals.failure(response.message())
            }

            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                t.message?.let { callbackAnimals.failure(it) }
            }

        })
    }

    override fun login(userLogin: UserLogin, callbackLogin: RepositoryCallback<User>) {
        val call = webService.login(userLogin)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    response.body()?.let { callbackLogin.success(it) }
                }
                else callbackLogin.failure(response.message())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { callbackLogin.failure(it) }
            }

        })
    }
}

