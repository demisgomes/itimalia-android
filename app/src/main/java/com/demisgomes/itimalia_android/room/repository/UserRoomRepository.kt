package com.demisgomes.itimalia_android.room.repository

import com.demisgomes.itimalia_android.domain.user.User
import com.demisgomes.itimalia_android.room.dao.UserDao
import com.demisgomes.itimalia_android.room.entities.UserEntity

class UserRoomRepository(private val userDao: UserDao) {

    suspend fun add(user: User){
        userDao.add(UserEntity.fromUser(user))
    }

    suspend fun get(id: Int): User{
        val userEntity = userDao.get(id)
        return userEntity.toUser()
    }
}