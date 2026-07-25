package com.example.whattowatch.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WatchLaterEntity::class],
    version = 1
)
@TypeConverters(MediaTypeConverter::class)

abstract class WatchLaterDatabase : RoomDatabase() {
    abstract fun watchLaterDao(): WatchLaterDao
}