package com.example.whattowatch.data.remote.network

import com.example.whattowatch.data.remote.dto.ImageDto
import com.example.whattowatch.data.remote.dto.JustWatchDto
import com.example.whattowatch.data.remote.dto.movie_details.MovieDetailsDto
import com.example.whattowatch.data.remote.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.remote.dto.movie_search.SearchedMovieDto
import com.example.whattowatch.data.remote.dto.movie_upcoming.UpcomingMovieSearchResultsDto
import com.example.whattowatch.data.remote.dto.tv_details.TvDetailsDto
import com.example.whattowatch.data.remote.dto.tv_search.SearchedTvDto
import com.example.whattowatch.data.remote.dto.tv_search.TvSearchResultsDto
import com.example.whattowatch.domain.MediaType

interface RemoteDataSource {
    suspend fun searchMovies(
        query: String
    ): Result<MovieSearchResultsDto>

    suspend fun getImage(imageUrlString: String?): Result<ByteArray>

    suspend fun searchTv(query: String): Result<TvSearchResultsDto>

    suspend fun getMovieDetails(id: Int): Result<MovieDetailsDto>

    suspend fun getTvDetails(id: Int): Result<TvDetailsDto>

    suspend fun getUpcomingMovies(): Result<UpcomingMovieSearchResultsDto>

    suspend fun getPopularMovies(): Result<MovieSearchResultsDto>

    suspend fun getTopRatedMovies(): Result<List<SearchedMovieDto>>

    suspend fun getNowPlayingMovies(): Result<MovieSearchResultsDto>

    suspend fun getMovieRecommendations(id: Int): Result<MovieSearchResultsDto>


    suspend fun getImages(id: Int, mediaType: MediaType): Result<List<ImageDto>>

    suspend fun getPopularTvShows(): Result<TvSearchResultsDto>

    suspend fun getTopRatedTvShows(): Result<List<SearchedTvDto>>

    suspend fun getTvRecommendations(id: Int): Result<TvSearchResultsDto>

    suspend fun getJustWatch(id: Int, mediaType: MediaType): Result<JustWatchDto>


}