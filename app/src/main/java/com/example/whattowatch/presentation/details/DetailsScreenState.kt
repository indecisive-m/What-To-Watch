package com.example.whattowatch.presentation.details

import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.model.Images
import com.example.whattowatch.domain.model.JustWatch

data class DetailsScreenState(
    val status: Status = Status.IDLE,
    val media: Media? = null,
    val mediaType: MediaType = MediaType.MOVIE,
    val errorMessage: String? = null,
    val isWatchLater: Boolean = false,
    val justWatch: JustWatch? = null,
    val images: List<Images> = emptyList(),
    val movieRecommendations: List<Movie> = emptyList(),
    val tvRecommendations: List<Tv> = emptyList()

)

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    ERROR
}



