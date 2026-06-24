package com.example.whattowatch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourites(vararg favourites: FavouritesEntity)

    @Query("DELETE FROM favourites WHERE id = :id")
    suspend fun removeFromFavourites(id: Int)

    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): Flow<List<FavouritesEntity>>

    @Query("SELECT * FROM favourites WHERE mediaType = 'MOVIE'")
    fun getAllMoviesFromFavourites(): Flow<List<FavouritesEntity>>

    @Query("SELECT * FROM favourites WHERE mediaType = 'TV'")
    fun getAllTvShowsFromFavourites(): Flow<List<FavouritesEntity>>

}