package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.tv_details.Networks
import com.example.whattowatch.domain.model.Networks as Domain

fun Networks.toDomain(): Domain {
    return Domain(
        id = id,
        logoPath = "https://image.tmdb.org/t/p/w500$logoPath",
        name = name,
        originCountry = originCountry
    )
}