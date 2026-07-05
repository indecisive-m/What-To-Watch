package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.movie_details.ProductionCountries
import com.example.whattowatch.domain.model.ProductionCountries as Domain

fun ProductionCountries.toDomain(): Domain {
    return Domain(
        iso = iso,
        name = name
    )

}