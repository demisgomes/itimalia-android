package com.demisgomes.itimalia_android.domain.error

data class ErrorResponse(
    val apiError: String,
    val message: String,
    val details: Map<String, List<String>>? = emptyMap()
)