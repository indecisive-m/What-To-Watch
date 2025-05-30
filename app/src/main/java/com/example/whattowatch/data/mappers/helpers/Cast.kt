package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.Cast
import com.example.whattowatch.domain.model.Cast as DomainCast

fun Cast.toDomain(): DomainCast {
    return DomainCast(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = if (profilePath != null) "https://image.tmdb.org/t/p/w185$profilePath" else null,
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )

}