package com.example.whattowatch.data.remote.mappers.helpers


import com.example.whattowatch.data.remote.dto.tv_details.TvCredits
import com.example.whattowatch.domain.model.TvCredits as DomainTvCredits


fun TvCredits.toDomain(): DomainTvCredits {
    return DomainTvCredits(
        tvCast = tvCast.map { it.toDomain() },
        tvCrew = tvCrew.map { it.toDomain() }
    )
}