package com.demisgomes.itimalia_android.repository

import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback

interface Repository {
   fun getAnimalsList(callbackAnimals: RepositoryCallback<List<Animal>>)

   fun login(userLogin: UserLogin, callbackLogin: RepositoryCallback<User>)
}