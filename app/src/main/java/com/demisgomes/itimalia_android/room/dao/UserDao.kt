package com.demisgomes.itimalia_android.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demisgomes.itimalia_android.room.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun get(id: Int): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(user: UserEntity)
}