package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.movie_details.BelongsToCollection
import com.example.whattowatch.domain.model.BelongsToCollection as DomainCollection


fun BelongsToCollection.toDomain(): DomainCollection {
    return DomainCollection(
        id = id,
        name = name,
        posterPath = posterPath?.let { "https://image.tmdb.org/t/p/w500$it" },
        backdropPath = backdropPath?.let { "https://image.tmdb.org/t/p/w780$it" }
    )
}