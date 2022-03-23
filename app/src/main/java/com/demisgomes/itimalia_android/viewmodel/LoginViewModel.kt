package com.demisgomes.itimalia_android.viewmodel

import android.util.Log
import android.util.Log.DEBUG
import androidx.lifecycle.*
import com.demisgomes.itimalia_android.domain.StatusResponse
import com.demisgomes.itimalia_android.domain.error.ErrorResponse
import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.domain.user.UserLogin
import com.demisgomes.itimalia_android.repository.Repository
import com.demisgomes.itimalia_android.retrofit.RepositoryCallback
import com.demisgomes.itimalia_android.room.UserDao
import com.demisgomes.itimalia_android.room.UserEntity
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository, private val userDao: UserDao) : ViewModel() {

    private val _responseViewModel = MutableLiveData<StatusResponse<User>>()
    val responseViewModel: LiveData<StatusResponse<User>> = _responseViewModel

    fun login(userLogin: UserLogin) =
        viewModelScope.launch {

            _responseViewModel.value = StatusResponse.Loading

            repository.login(userLogin, object : RepositoryCallback<User> {
                override fun success(t: User) {
                    viewModelScope.launch {
                        userDao.add(UserEntity(t.id!!, t.email, t.birthDate, t.gender.toString(), t.name, t.phone, t.role.toString(), t.creationDate, t.token))
                        val userEntity = userDao.get(t.id!!)
                        Log.d("USER-ENTITY", userEntity.toString())
                    }
                     _responseViewModel.value = StatusResponse.Success(t)
                }

                override fun failure(errorResponse: ErrorResponse) {
                    _responseViewModel.value = StatusResponse.Error(errorResponse)
                }

            })
        }
}