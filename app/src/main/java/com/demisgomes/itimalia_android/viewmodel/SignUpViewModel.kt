package com.demisgomes.itimalia_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demisgomes.itimalia_android.domain.error.ErrorResponse
import com.demisgomes.itimalia_android.domain.user.NewUser
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: Repository): ViewModel() {
    private val _responseViewModel = MutableLiveData<ResponseViewModel<User>>()
    val responseViewModel: LiveData<ResponseViewModel<User>> = _responseViewModel

    fun signUp(newUser: NewUser) =
        viewModelScope.launch {
            repository.signUp(newUser, object : RepositoryCallback<User> {
                override fun success(t: User) {
                    _responseViewModel.value = ResponseViewModel(t)
                }

                override fun failure(errorResponse: ErrorResponse) {
                    _responseViewModel.value = ResponseViewModel(errorResponse = errorResponse)
                }

            })
        }
}