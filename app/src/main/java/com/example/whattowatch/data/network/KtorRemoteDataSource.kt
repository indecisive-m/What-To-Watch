package com.example.whattowatch.data.network

import com.example.whattowatch.BuildConfig
import com.example.whattowatch.data.dto.MovieSearchResultsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers


private const val BASE_URL = "https://api.themoviedb.org/3/"

private const val BEARER_TOKEN = BuildConfig.BEARER_TOKEN


class KtorRemoteDataSource(
    private val httpClient: HttpClient
) : RemoteDataSource {

    override suspend fun searchMovies(query: String): MovieSearchResultsDto {

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
}