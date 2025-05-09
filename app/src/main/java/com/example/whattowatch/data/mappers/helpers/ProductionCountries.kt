package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.ProductionCountries
import com.example.whattowatch.domain.model.ProductionCountries as Domain

fun ProductionCountries.toDomain(): Domain {
    return Domain(
        iso = iso,
        name = name
    )

}