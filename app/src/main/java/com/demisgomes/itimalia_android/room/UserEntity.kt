package com.demisgomes.itimalia_android.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demisgomes.itimalia_android.domain.user.Gender
import com.demisgomes.itimalia_android.domain.user.UserRole
import java.util.*

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int,
    val email: String,
    val birthDate: Date,
    val gender: String,
    val name: String,
    val phone: String,
    val role: String,
    val creationDate: Date,
    val token: String,
    //val adoptedAnimals: List<AnimalWithoutAdopter> = emptyList()
)

