package com.demisgomes.itimalia_android.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.demisgomes.itimalia_android.domain.StatusResponse
import com.demisgomes.itimalia_android.domain.error.ErrorResponse
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import com.demisgomes.itimalia_android.room.dao.UserDao
import com.demisgomes.itimalia_android.room.entities.UserEntity
import com.demisgomes.itimalia_android.room.repository.UserRoomRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository, private val userRoomRepository: UserRoomRepository) : ViewModel() {

    private val _responseViewModel = MutableLiveData<StatusResponse<User>>()
    val responseViewModel: LiveData<StatusResponse<User>> = _responseViewModel

    fun login(userLogin: UserLogin) =
        viewModelScope.launch {

            _responseViewModel.value = StatusResponse.Loading

            repository.login(userLogin, object : RepositoryCallback<User> {
                override fun success(t: User) {
                    viewModelScope.launch {
                        userRoomRepository.add(t)
                        val user = userRoomRepository.get(t.id!!)
                        Log.d("USER-", user.toString())
                    }
                     _responseViewModel.value = StatusResponse.Success(t)
                }

                override fun failure(errorResponse: ErrorResponse) {
                    _responseViewModel.value = StatusResponse.Error(errorResponse)
                }

            })
        }
}