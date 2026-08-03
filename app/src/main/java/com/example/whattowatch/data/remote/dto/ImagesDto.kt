package com.example.whattowatch.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImagesObjectDto(
    val backdrops: List<ImageDto>,
    val logos: List<ImageDto>,
    val posters: List<ImageDto>
)


@Serializable
data class ImageDto(
    @SerialName("aspect_ratio")
    val aspectRatio: Double,
    val height: Int,
    @SerialName("iso_3166_1")
    val iso3166: String?,
    @SerialName("iso_639_1")
    val iso639: String?,
    @SerialName("file_path")
    val filePath: String,
    @SerialName("vote_average")
    val averageVote: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    val width: Int
)