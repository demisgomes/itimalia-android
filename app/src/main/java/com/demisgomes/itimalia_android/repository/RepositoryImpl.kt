package com.demisgomes.itimalia_android.repository

import com.demisgomes.itimalia_android.domain.animal.Animal
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
}

