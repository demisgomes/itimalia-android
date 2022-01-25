package com.demisgomes.itimalia_android.domain.user

import java.util.*

data class NewUser(
    val email: String,
    val password: String,
    val birthDate: Date = Calendar.getInstance().time,
    val gender: Gender = Gender.NOT_DECLARED,
    val name: String,
    val phone: String
)