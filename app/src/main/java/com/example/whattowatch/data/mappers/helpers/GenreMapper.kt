package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.Genres
import com.example.whattowatch.domain.model.Genres as DomainGenre

fun Genres.toDomain(): DomainGenre {
    return DomainGenre(
        id = id,
        name = name
    )
}