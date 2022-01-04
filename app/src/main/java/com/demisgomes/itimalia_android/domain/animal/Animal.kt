package com.demisgomes.itimalia_android.domain.animal

import java.io.Serializable
import java.util.Date

data class Animal(
    val id: Int?,
    val name: String,
    val age: Int?,
    val timeUnit : TimeUnit?,
    val specie: Specie,
    val description: String,
    val creationDate: Date,
    val modificationDate: Date,
    val status: AnimalStatus,
    val deficiencies: List<AnimalDeficiency>,
    val sex: AnimalSex,
    val size: AnimalSize,
    val castrated: Boolean,
    val createdById: Int,
    var imageUrl: String = ""
    //val adoptedBy : UserPublicInfo? = null
) : Serializable