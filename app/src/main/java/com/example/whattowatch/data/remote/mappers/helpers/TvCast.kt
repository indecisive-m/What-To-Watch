package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.tv_details.TvCast
import com.example.whattowatch.domain.model.TvCast as DomainTvCast


fun TvCast.toDomain(): DomainTvCast {
    return DomainTvCast(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath?.let { "https://image.tmdb.org/t/p/w185$it" },
        character = character,
        creditId = creditId,
        order = order
    )

}

