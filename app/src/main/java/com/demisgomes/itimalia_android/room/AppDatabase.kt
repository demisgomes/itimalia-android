package com.demisgomes.itimalia_android.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demisgomes.itimalia_android.room.dao.UserDao
import com.demisgomes.itimalia_android.room.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var instance : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase{
            return instance ?: synchronized(this){
                instance ?: Room.databaseBuilder(context, AppDatabase::class.java, "itimalia").build().also { instance = it }
            }
        }
    }
}