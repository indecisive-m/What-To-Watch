package com.example.whattowatch.data.repository

import com.example.whattowatch.data.network.KtorRemoteDataSource
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.Movie

class DefaultMediaRepository(
    private val remoteDataSource: KtorRemoteDataSource
) : MediaRepository {

    override suspend fun searchMovies(query: String): Result<List<Movie>> {

        return remoteDataSource.searchMovies(query)
    }
}