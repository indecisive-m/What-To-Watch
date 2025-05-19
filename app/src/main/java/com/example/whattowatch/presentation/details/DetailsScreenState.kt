package com.example.whattowatch.presentation.details

import com.example.whattowatch.domain.Media

data class DetailsScreenState(
    val status: Status = Status.IDLE,
    val media: Media? = null,
    val mediaOption: MediaOption = MediaOption.MOVIE,
    val errorMessage: String? = null,
)

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}

enum class MediaOption {
    MOVIE,
    TV
}

