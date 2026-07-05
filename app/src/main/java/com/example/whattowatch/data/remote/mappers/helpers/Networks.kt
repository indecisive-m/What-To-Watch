package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.tv_details.Networks
import com.example.whattowatch.domain.model.Networks as Domain

fun Networks.toDomain(): Domain {
    return Domain(
        id = id,
        logoPath = "https://image.tmdb.org/t/p/w500$logoPath",
        name = name,
        originCountry = originCountry
    )
}