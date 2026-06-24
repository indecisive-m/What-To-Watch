package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.database.FavouritesEntity
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.MovieDetails
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.TvDetails

fun Media.toFavouritesEntity(): FavouritesEntity {
    return when (this) {
        is Movie -> FavouritesEntity(
            id = id,
            title = title,
            releaseDate = releaseDate,
            overview = overview,
            averageVote = averageVote,
            name = null,
            firstAirDate = null,
            mediaType = MediaType.MOVIE,
        )

        is MovieDetails ->
            FavouritesEntity(
                id = id,
                title = title,
                releaseDate = releaseDate,
                overview = overview,
                averageVote = averageVote,
                name = null,
                firstAirDate = null,
                mediaType = MediaType.MOVIE
            )

        is Tv -> FavouritesEntity(
            id = id,
            title = null,
            releaseDate = null,
            overview = overview,
            averageVote = averageVote,
            name = name,
            firstAirDate = firstAirDate,
            mediaType = MediaType.TV
        )

        is TvDetails -> FavouritesEntity(
            id = id,
            title = null,
            releaseDate = null,
            overview = overview,
            averageVote = averageVote,
            name = name,
            firstAirDate = firstAirDate,
            mediaType = MediaType.TV
        )
    }
}

fun FavouritesEntity.toMedia(): Media {
    return if (mediaType == MediaType.MOVIE) {
        Movie(
            id = id,
            adult = false,
            posterPath = null,
            backdropPath = null,
            popularity = 0.0,
            overview = overview ?: "",
            language = "",
            averageVote = averageVote ?: 0.0,
            voteCount = 0,
            genreIds = emptyList(),
            title = title,
            originalTitle = null,
            releaseDate = releaseDate,
            video = false
        )
    } else {
        Tv(
            id = id,
            adult = false,
            posterPath = null,
            backdropPath = null,
            popularity = 0.0,
            overview = overview ?: "",
            language = "",
            averageVote = averageVote ?: 0.0,
            voteCount = 0,
            genreIds = emptyList(),
            name = name,
            originCountry = null,
            originalName = null,
            firstAirDate = firstAirDate
        )
    }
}