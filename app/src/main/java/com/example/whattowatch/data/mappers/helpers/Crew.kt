package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.Crew
import com.example.whattowatch.domain.model.Crew as DomainCrew

fun Crew.toDomain(): DomainCrew {
    return DomainCrew(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = if (profilePath != null) "https://image.tmdb.org/t/p/w185$profilePath" else null,
        creditId = creditId,
        department = department,
        job = job
    )
}