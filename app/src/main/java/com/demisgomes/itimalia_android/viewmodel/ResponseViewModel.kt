package com.demisgomes.itimalia_android.viewmodel

import com.demisgomes.itimalia_android.domain.error.ErrorResponse

data class ResponseViewModel<T>(val response : T? = null, val errorResponse: ErrorResponse? = null)