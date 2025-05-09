package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.BelongsToCollection
import com.example.whattowatch.domain.model.BelongsToCollection as DomainCollection


fun BelongsToCollection.toDomain(): DomainCollection {
    return DomainCollection(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )
}