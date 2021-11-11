package com.demisgomes.itimalia_android.repository

import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback

interface Repository {
   fun getAnimalsList(callbackAnimals: RepositoryCallback<List<Animal>>)
}