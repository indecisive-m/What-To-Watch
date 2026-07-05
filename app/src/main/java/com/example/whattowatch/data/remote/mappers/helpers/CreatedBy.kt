package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.tv_details.CreatedBy
import com.example.whattowatch.domain.model.CreatedBy as Domain

fun CreatedBy.toDomain(): Domain {
    return Domain(
        id = id,
        creditId = creditId,
        name = name,
        gender = gender,
        profilePath = "https://image.tmdb.org/t/p/w185$profilePath",
        originalName = originalName
    )
}