package com.demisgomes.itimalia_android.viewmodel

import androidx.lifecycle.*
import com.demisgomes.itimalia_android.domain.animal.Animal
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {

    private val _responseViewModel = MutableLiveData<ResponseViewModel<User>>()
    val responseViewModel: LiveData<ResponseViewModel<User>> = _responseViewModel

    fun login(userLogin: UserLogin) =
        viewModelScope.launch {
            repository.login(userLogin, object : RepositoryCallback<User> {
                override fun success(t: User) {
                    _responseViewModel.value = ResponseViewModel(t)
                }

                override fun failure(message: String) {
                    _responseViewModel.value = ResponseViewModel(errorMessage = message)
                }

            })
        }
}