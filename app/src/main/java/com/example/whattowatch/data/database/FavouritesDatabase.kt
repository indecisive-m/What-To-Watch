package com.example.whattowatch.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [FavouritesEntity::class],
    version = 1
)
@TypeConverters(MediaTypeConverter::class)

abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}