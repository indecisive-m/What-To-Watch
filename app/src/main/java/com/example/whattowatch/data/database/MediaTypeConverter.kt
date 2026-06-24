package com.example.whattowatch.data.database

import androidx.room.TypeConverter
import com.example.whattowatch.domain.MediaType

object MediaTypeConverter {

    @TypeConverter
    fun fromMediaType(mediaType: MediaType): String =
        mediaType.name

    @TypeConverter
    fun toMediaType(value: String): MediaType =
        MediaType.valueOf(value)
}