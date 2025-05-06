package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.dto.SearchedMovieDto
import com.example.whattowatch.domain.Movie

fun SearchedMovieDto.toMovie(): Movie {

    // Finish this. Return proper url for the image.


    return Movie(
        id = id,
        title = title,
        adult = adult,
        posterPath = TODO(),
        backdropPath = TODO(),
        genreIds = TODO(),
        language = TODO(),
        originalTitle = TODO(),
        overview = TODO(),
        popularity = TODO(),
        releaseDate = TODO(),
        video = TODO(),
        averageVote = TODO(),
        voteCount = TODO()
    )
}