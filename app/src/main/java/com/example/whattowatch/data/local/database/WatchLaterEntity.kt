package com.example.whattowatch.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.whattowatch.domain.MediaType

@Entity(tableName = "watch_later")
data class WatchLaterEntity(
    @PrimaryKey val id: Int,
    val title: String?,
    val releaseDate: String?,
    val overview: String?,
    val averageVote: Double?,
    val name: String?,
    val firstAirDate: String?,
    val mediaType: MediaType,
    val imageLink: String?

)
