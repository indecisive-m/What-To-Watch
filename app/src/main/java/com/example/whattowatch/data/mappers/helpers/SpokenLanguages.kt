package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.SpokenLanguages
import com.example.whattowatch.domain.model.SpokenLanguages as Domain

fun SpokenLanguages.toDomain(): Domain {
    return Domain(
        englishName = englishName,
        iso = iso,
        name = name
    )

}