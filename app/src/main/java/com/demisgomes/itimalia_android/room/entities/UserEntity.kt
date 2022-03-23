package com.demisgomes.itimalia_android.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demisgomes.itimalia_android.domain.user.Gender
import com.demisgomes.itimalia_android.domain.user.User
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
) {

    fun toUser() = User(id, email, birthDate, Gender.valueOf(gender), name, phone, UserRole.valueOf(role), creationDate, null, token)

    companion object{
        fun fromUser(user: User) = UserEntity(user.id!!, user.email, user.birthDate, user.gender.toString(), user.name, user.phone, user.role.toString(), user.creationDate, user.token)
    }
}

