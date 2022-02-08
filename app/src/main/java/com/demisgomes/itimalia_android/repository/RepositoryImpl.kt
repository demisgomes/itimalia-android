package com.demisgomes.itimalia_android.repository

import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.domain.error.ErrorResponse
import com.demisgomes.itimalia_android.domain.user.NewUser
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import com.demisgomes.itimalia_android.retrofit.WebService
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(private val webService: WebService, private val gson: Gson) : Repository {
    override fun getAnimalsList(callbackAnimals: RepositoryCallback<List<Animal>>) {
        val call = webService.getAnimals()

        call.enqueue(object : Callback<List<Animal>> {
            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                if (response.isSuccessful) {
                    response.body()?.let { callbackAnimals.success(it) }
                }
                else{
                    val errorResponse: ErrorResponse? = gson.fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
                    if (errorResponse != null) {
                        callbackAnimals.failure(errorResponse)
                    }
                }
             }

            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                t.message?.let { callbackAnimals.failure(ErrorResponse("CLIENT_ERROR", it)) }
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
                else {
                    val errorResponse: ErrorResponse? = gson.fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
                    if (errorResponse != null) {
                        callbackLogin.failure(errorResponse)
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { callbackLogin.failure(ErrorResponse("CLIENT_ERROR", it)) }
            }

        })
    }

    override fun signUp(newUser: NewUser, callbackLogin: RepositoryCallback<User>) {
        val call = webService.signUp(newUser)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    response.body()?.let { callbackLogin.success(it) }
                }
                else {
                    val errorResponse: ErrorResponse? = gson.fromJson(response.errorBody()?.charStream(), ErrorResponse::class.java)
                    if (errorResponse != null) {
                        callbackLogin.failure(errorResponse)
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.message?.let { callbackLogin.failure(ErrorResponse("CLIENT_ERROR", it)) }
            }

        })
    }
}

