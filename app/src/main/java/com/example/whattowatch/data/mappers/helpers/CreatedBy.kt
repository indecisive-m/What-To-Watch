package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.tv_details.CreatedBy
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