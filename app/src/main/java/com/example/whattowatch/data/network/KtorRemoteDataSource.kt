package com.example.whattowatch.data.network

import com.example.whattowatch.BuildConfig
import com.example.whattowatch.data.dto.movie_details.MovieDetailsResultsDto
import com.example.whattowatch.data.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.dto.tv_details.TvDetailsResultsDto
import com.example.whattowatch.data.dto.tv_search.TvSearchResultsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers


private const val BASE_URL = "https://api.themoviedb.org/3"
private const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

private const val BEARER_TOKEN = BuildConfig.BEARER_TOKEN


class KtorRemoteDataSource(
    private val httpClient: HttpClient
) : RemoteDataSource {

    override suspend fun searchMovies(query: String): Result<MovieSearchResultsDto> {

        return httpClient.get("$BASE_URL/search/movie") {
            headers {
                append(
                    "Authorization",
                    "Bearer $BEARER_TOKEN"
                )
            }
            url {
                parameters.append(
                    "query",
                    query
                )
            }

        }
            .body()
    }

    override suspend fun getImages(imageUrlString: String): Result<ByteArray> {
        return httpClient.get("$BASE_IMG_URL$imageUrlString") {
            headers {
                append(
                    "Authorization",
                    "Bearer $BEARER_TOKEN"
                )
            }
        }
            .body()
    }

    override suspend fun searchTv(query: String): Result<TvSearchResultsDto> {
        return httpClient.get("$BASE_URL/search/tv") {
            headers {
                append(
                    "Authorization",
                    "Bearer $BEARER_TOKEN"
                )
            }
            url {
                parameters.append(
                    "query",
                    query
                )
            }

        }
            .body()
    }

    override suspend fun getMovieDetails(id: Int): Result<MovieDetailsResultsDto> {
        return httpClient.get("$BASE_URL/movie/${id.toString()}") {
            headers {
                append(
                    "Authorization",
                    "Bearer $BEARER_TOKEN"
                )
            }
        }
            .body()
    }

    override suspend fun getTvDetails(id: Int): Result<TvDetailsResultsDto> {
        return httpClient.get("$BASE_URL/tv/${id.toString()}") {
            headers {
                append(
                    "Authorization",
                    "Bearer $BEARER_TOKEN"
                )
            }
        }
            .body()
    }
}
