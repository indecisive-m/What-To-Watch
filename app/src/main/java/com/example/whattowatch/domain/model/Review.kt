package com.example.whattowatch.domain.model

data class Review(
    val author: String,
    val authorDetails: AuthorDetails,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)


data class AuthorDetails(
    val name: String?,
    val username: String,
    val avatarPath: String?,
    val rating: Double?,
)