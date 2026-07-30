package com.example.whattowatch.data.remote.mappers

import com.example.whattowatch.data.remote.dto.JustWatchDto
import com.example.whattowatch.data.remote.mappers.helpers.toDomain
import com.example.whattowatch.domain.model.JustWatch

fun JustWatchDto.toJustWatch(): JustWatch {
    return JustWatch(
        link = link,
        flatrate = flatrate?.map { it.toDomain() },
        buy = buy?.map { it.toDomain() },
        rent = rent?.map { it.toDomain() }
    )
}