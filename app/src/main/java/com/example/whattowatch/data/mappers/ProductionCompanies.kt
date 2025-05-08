package com.example.whattowatch.data.mappers

import com.example.whattowatch.data.dto.movie_details.ProductionCompanies
import com.example.whattowatch.domain.model.ProductionCompanies as Domain

fun ProductionCompanies.toDomain(): Domain {
    return Domain(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}