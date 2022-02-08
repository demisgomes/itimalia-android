package com.demisgomes.itimalia_android.retrofit

import com.demisgomes.itimalia_android.domain.error.ErrorResponse

interface RepositoryCallback<T>{
    fun success(t: T)
    fun failure(errorResponse: ErrorResponse)
}