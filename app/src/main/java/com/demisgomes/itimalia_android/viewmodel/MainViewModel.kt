package com.demisgomes.itimalia_android.viewmodel

import androidx.lifecycle.*
import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.repository.RepositoryImpl
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import com.demisgomes.itimalia_android.retrofit.RetroConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: Repository) : ViewModel() {

    private val _responseViewModel = MutableLiveData<ResponseViewModel<List<Animal>>>()
    val responseViewModel: LiveData<ResponseViewModel<List<Animal>>> = _responseViewModel

    init {
        viewModelScope.launch {
            repository.getAnimalsList(object : RepositoryCallback<List<Animal>> {
                override fun success(t: List<Animal>) {
                    _responseViewModel.value = ResponseViewModel(t)
                }

                override fun failure(message: String) {
                    _responseViewModel.value = ResponseViewModel(errorMessage = message)
                }

            })
        }
    }
}