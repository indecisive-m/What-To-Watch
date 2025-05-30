package com.example.whattowatch.data.mappers.helpers

import com.example.whattowatch.data.dto.movie_details.AuthorDetails
import com.example.whattowatch.data.dto.movie_details.Review
import com.example.whattowatch.domain.model.AuthorDetails as DomainAuthorDetails
import com.example.whattowatch.domain.model.Review as DomainReview

fun Review.toDomain(): DomainReview {
    return DomainReview(
        author = author,
        authorDetails = authorDetails.toDomain(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url
    )
}


fun AuthorDetails.toDomain(): DomainAuthorDetails {
    return DomainAuthorDetails(
        name = name,
        username = username,
        avatarPath = if (avatarPath != null) "https://image.tmdb.org/t/p/w185$avatarPath" else null,
        rating = rating
    )
}