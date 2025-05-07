package com.example.whattowatch.data.repository

import com.example.whattowatch.data.dto.MovieSearchResultsDto
import com.example.whattowatch.data.mappers.toMovie
import com.example.whattowatch.data.mappers.toTVShow
import com.example.whattowatch.data.network.KtorRemoteDataSource
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.TVShow


class DefaultMediaRepository(
    private val remoteDataSource: KtorRemoteDataSource
) : MediaRepository {

    override suspend fun searchMovies(query: String): Result<List<Movie>> {

        return remoteDataSource.searchMovies(query)
            .map { dto: MovieSearchResultsDto ->
                dto.results.map {
                    it.toMovie()
                }
            }

    }

    override suspend fun searchTVShow(query: String): Result<List<TVShow>> {
        return remoteDataSource.searchTVShow(query)
            .map { dto ->
                dto.results.map {
                    it.toTVShow()
                }
            }
    }

    // Need to implement this once I have added the saving to local database.
    // Will use coil to load images but when save to watch later list is completed then use this to get byte array
    // to store images for offline use.


    override suspend fun getImages(imageUrlString: String): Result<ByteArray> {
        return remoteDataSource.getImages(imageUrlString)
    }
}


