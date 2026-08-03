package com.example.whattowatch.data.remote.mappers.helpers

import com.example.whattowatch.data.remote.dto.ImageDto
import com.example.whattowatch.domain.model.Images

fun ImageDto.toImages(): Images {
    return Images(
        aspectRatio = aspectRatio,
        height = height,
        iso3166 = iso3166,
        iso639 = iso639,
        filePath = filePath.let { "https://image.tmdb.org/t/p/w500$it" },
        averageVote = averageVote,
        voteCount = voteCount,
        width = width
    )
}