package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.Credits
import com.example.whattowatch.domain.model.Credits as DomainCredits


fun Credits.toDomain(): DomainCredits {
    return DomainCredits(
        cast = cast.map { it.toDomain() },
        crew = crew.map { it.toDomain() }
    )
}