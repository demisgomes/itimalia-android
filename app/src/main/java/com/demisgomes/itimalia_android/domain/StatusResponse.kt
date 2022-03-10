package com.demisgomes.itimalia_android.domain

import androidx.annotation.Keep
import com.demisgomes.itimalia_android.domain.error.ErrorResponse

@Keep
sealed class StatusResponse<out R>{
    data class Success<T>(val data: T): StatusResponse<T>()
    object Loading: StatusResponse<Nothing>()
    data class Error(val errorResponse: ErrorResponse): StatusResponse<Nothing>()
}
