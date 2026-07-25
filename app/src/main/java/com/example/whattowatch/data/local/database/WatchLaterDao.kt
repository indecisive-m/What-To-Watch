package com.example.whattowatch.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface WatchLaterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchLater(vararg favourites: WatchLaterEntity)

    @Query("DELETE FROM watch_later WHERE id = :id")
    suspend fun removeFromWatchLater(id: Int)

    @Query("SELECT * FROM watch_later")
    fun getAllWatchLater(): Flow<List<WatchLaterEntity>>

    @Query("SELECT * FROM watch_later WHERE mediaType = 'MOVIE'")
    fun getAllMoviesFromWatchLater(): Flow<List<WatchLaterEntity>>

    @Query("SELECT * FROM watch_later WHERE mediaType = 'TV'")
    fun getAllTvShowsFromWatchLater(): Flow<List<WatchLaterEntity>>

}