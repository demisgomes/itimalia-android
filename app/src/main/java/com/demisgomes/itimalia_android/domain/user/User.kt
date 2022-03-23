package com.demisgomes.itimalia_android.domain.user

import java.util.Date

data class User(
    val id: Int?,
    val email: String,
    val birthDate: Date,
    val gender: Gender,
    val name: String,
    val phone: String,
    val role: UserRole,
    val creationDate: Date,
    val modificationDate: Date?,
    val token: String,
    //val adoptedAnimals: List<AnimalWithoutAdopter> = emptyList()
)