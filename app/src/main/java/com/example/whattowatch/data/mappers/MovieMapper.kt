package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.dto.movie_details.MovieDetailsDto
import com.example.whattowatch.data.dto.movie_search.SearchedMovieDto
import com.example.whattowatch.data.mappers.helpers.toDomain
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.MovieDetails

fun SearchedMovieDto.toMovie(): Movie {

// Will need to add a null check for the images

    return Movie(
        id = id,
        title = title,
        adult = adult,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        backdropPath = "https://image.tmdb.org/t/p/w500$backdropPath",
        genreIds = genreIds,
        language = language,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        video = video,
        averageVote = averageVote,
        voteCount = voteCount
    )
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {

    return MovieDetails(
        id = id,
        title = title,
        adult = adult,
        posterPath = "https://image.tmdb.org/t/p/w500$posterPath",
        backdropPath = "https://image.tmdb.org/t/p/w500$backdropPath",
        genres = genres.map { it.toDomain() },
        language = language,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        releaseDate = releaseDate,
        video = video,
        averageVote = averageVote,
        voteCount = voteCount,
        belongsToCollection = belongsToCollection.toDomain(),
        budget = budget,
        homepage = homepage,
        imdbId = imdbId,
        productionCompanies = productionCompanies.map { it.toDomain() },
        productionCountries = productionCountries.map { it.toDomain() },
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.toDomain() },
        status = status,
        tagline = tagline,
    )
}


