package com.demisgomes.itimalia_android.viewmodel

data class ResponseViewModel<T>(val response : T? = null, val errorMessage: String? = null)