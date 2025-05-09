package com.example.whattowatch.data.repository

import com.example.whattowatch.data.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.mappers.toMovie
import com.example.whattowatch.data.mappers.toMovieDetails
import com.example.whattowatch.data.mappers.toTv
import com.example.whattowatch.data.mappers.toTvDetails
import com.example.whattowatch.data.network.KtorRemoteDataSource
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.MovieDetails
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.TvDetails


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

    override suspend fun searchTv(query: String): Result<List<Tv>> {
        return remoteDataSource.searchTv(query)
            .map { dto ->
                dto.results.map {
                    it.toTv()
                }
            }
    }

    override suspend fun getMovieDetails(id: Int): Result<List<MovieDetails>> {
        return remoteDataSource.getMovieDetails(id)
            .map { dto ->
                dto.results.map {
                    it.toMovieDetails()
                }

            }
    }

    override suspend fun getTvDetails(id: Int): Result<List<TvDetails>> {
        return remoteDataSource.getTvDetails(id)
            .map { dto ->
                dto.results.map {
                    it.toTvDetails()
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






