package com.example.whattowatch.data.remote.mappers.helpers


import com.example.whattowatch.data.remote.dto.tv_details.TvCrew
import com.example.whattowatch.domain.model.TvCrew as DomainTvCrew


fun TvCrew.toDomain(): DomainTvCrew {
    return DomainTvCrew(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath?.let { "https://image.tmdb.org/t/p/w185$it" },
        creditId = creditId,
        department = department,
        job = job,
    )

}

