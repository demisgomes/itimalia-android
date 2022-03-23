package com.demisgomes.itimalia_android.room

import androidx.room.TypeConverter
import java.util.*

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {
    @TypeConverter fun dateToTimestamp(date: Date): Long = date.time

    @TypeConverter fun timestampToDate(value: Long): Date = Date(value)
}