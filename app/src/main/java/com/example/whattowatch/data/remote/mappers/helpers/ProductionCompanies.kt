package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.movie_details.ProductionCompanies
import com.example.whattowatch.domain.model.ProductionCompanies as Domain

fun ProductionCompanies.toDomain(): Domain {
    return Domain(
        id = id,
        logoPath = if (logoPath != null) "https://image.tmdb.org/t/p/w300$logoPath" else null,
        name = name,
        originCountry = originCountry
    )
}