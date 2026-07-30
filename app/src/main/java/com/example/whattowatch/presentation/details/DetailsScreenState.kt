package com.example.whattowatch.presentation.details

import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.model.JustWatch

data class DetailsScreenState(
    val status: Status = Status.IDLE,
    val media: Media? = null,
    val mediaType: MediaType = MediaType.MOVIE,
    val errorMessage: String? = null,
    val isWatchLater: Boolean = false,
    val justWatch: JustWatch? = null
)

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}



