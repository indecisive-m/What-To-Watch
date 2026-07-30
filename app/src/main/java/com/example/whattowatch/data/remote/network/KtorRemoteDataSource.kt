package com.example.whattowatch.data.remote.network

import android.util.Log
import com.example.whattowatch.BuildConfig
import com.example.whattowatch.data.remote.dto.JustWatchDto
import com.example.whattowatch.data.remote.dto.movie_details.MovieDetailsDto
import com.example.whattowatch.data.remote.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.remote.dto.movie_upcoming.UpcomingMovieSearchResultsDto
import com.example.whattowatch.data.remote.dto.tv_details.TvDetailsDto
import com.example.whattowatch.data.remote.dto.tv_search.TvSearchResultsDto
import com.example.whattowatch.domain.MediaType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject


private const val BASE_URL = "https://api.themoviedb.org/3"
private const val BASE_IMG_URL = "https://image.tmdb.org/t/p/w500"

private const val BEARER_TOKEN = BuildConfig.BEARER_TOKEN


class KtorRemoteDataSource(
    private val httpClient: HttpClient
) : RemoteDataSource {

    override suspend fun searchMovies(query: String): Result<MovieSearchResultsDto> {
        return try {
            val response: HttpResponse = httpClient.get("$BASE_URL/search/movie") {
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
            val resultsDto: MovieSearchResultsDto = response.body()


            Result.success(resultsDto)
        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }
    }

    override suspend fun getImage(imageUrlString: String?): Result<ByteArray> {
        return try {
            val response =
                httpClient.get("$BASE_IMG_URL$imageUrlString") {
                    headers {
                        append(
                            "Authorization",
                            "Bearer $BEARER_TOKEN"
                        )
                    }
                }
            val results = response.body<ByteArray>()


            Result.success(results)
        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }
    }


    override suspend fun searchTv(query: String): Result<TvSearchResultsDto> {
        return try {
            val response = httpClient.get("$BASE_URL/search/tv") {
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
            val resultsDto: TvSearchResultsDto = response.body()

            Log.d("tv", resultsDto.toString())


            Result.success((resultsDto))

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }
    }


    override suspend fun getMovieDetails(id: Int): Result<MovieDetailsDto> {

        return try {
            val response: HttpResponse =
                httpClient.get("$BASE_URL/movie/${id.toString()}?append_to_response=credits,reviews,language=en-US") {
                    headers {
                        append(
                            "Authorization",
                            "Bearer $BEARER_TOKEN"
                        )
                    }
                }

            val results: MovieDetailsDto = response.body()


            Result.success(results)


        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }
    }


    override suspend fun getTvDetails(id: Int): Result<TvDetailsDto> {

        return try {

            val response: HttpResponse =
                httpClient.get("$BASE_URL/tv/${id.toString()}?append_to_response=credits,reviews,language=en-US") {
                    headers {
                        append(
                            "Authorization",
                            "Bearer $BEARER_TOKEN"
                        )
                    }
                }

            val results: TvDetailsDto = response.body()


            Result.success(results)


        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }
    }

    override suspend fun getUpcomingMovies(): Result<UpcomingMovieSearchResultsDto> {

        return try {
            val response: HttpResponse = httpClient.get("$BASE_URL/movie/upcoming") {
                headers {
                    append(
                        "Authorization",
                        "Bearer $BEARER_TOKEN"
                    )
                }
            }

            val results: UpcomingMovieSearchResultsDto = response.body()


            Result.success(results)

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }

    }

    override suspend fun getPopularMovies(): Result<MovieSearchResultsDto> {
        return try {
            val response: HttpResponse =
                httpClient.get("$BASE_URL/movie/popular?language=en-US&page=1") {
                    headers {
                        append(
                            "Authorization",
                            "Bearer $BEARER_TOKEN"
                        )
                    }
                }


            val results: MovieSearchResultsDto = response.body()


            Result.success(results)

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }

    }

    override suspend fun getTopRatedMovies(): Result<MovieSearchResultsDto> {
        return try {
            val response: HttpResponse = httpClient.get("$BASE_URL/movie/top_rated?page=1") {
                headers {
                    append(
                        "Authorization",
                        "Bearer $BEARER_TOKEN"
                    )
                }
            }


            val results: MovieSearchResultsDto = response.body()


            Result.success(results)

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }

    }

    override suspend fun getNowPlayingMovies(): Result<MovieSearchResultsDto> {
        return try {
            val response: HttpResponse = httpClient.get("$BASE_URL/movie/now_playing") {
                headers {
                    append(
                        "Authorization",
                        "Bearer $BEARER_TOKEN"
                    )
                }
            }


            val results: MovieSearchResultsDto = response.body()


            Result.success(results)

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }
    }

    override suspend fun getPopularTvShows(): Result<TvSearchResultsDto> {
        return try {
            val response: HttpResponse = httpClient.get(
                "$BASE_URL/tv/popular"
            ) {
                headers {
                    append(
                        "Authorization",
                        "Bearer $BEARER_TOKEN"
                    )
                }
            }


            val results: TvSearchResultsDto = response.body()


            Result.success(results)

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }

    }

    override suspend fun getTopRatedTvShows(): Result<TvSearchResultsDto> {
        return try {
            val response: HttpResponse = httpClient.get(
                "$BASE_URL/tv/top_rated"
            ) {
                headers {
                    append(
                        "Authorization",
                        "Bearer $BEARER_TOKEN"
                    )
                }
            }


            val results: TvSearchResultsDto = response.body()


            Result.success(results)

        } catch (e: ClientRequestException) {
            Log.d(
                "test",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test",
                e.toString()
            )

            Result.failure(e)
        }

    }

    override suspend fun getJustWatch(id: Int, mediaType: MediaType): Result<JustWatchDto> {
        return try {
            val response: HttpResponse = httpClient.get(
                "$BASE_URL/${mediaType.name.lowercase()}/${id}/watch/providers"
            ) {
                headers {
                    append(
                        "Authorization",
                        "Bearer $BEARER_TOKEN"
                    )
                }
            }


            val responseBody: JsonObject = response.body() as JsonObject

            val jsonObject = responseBody["results"]?.jsonObject

            val jsonElement =
                jsonObject?.get("GB") ?: throw NoSuchElementException("GB was not found")

            val justWatch = Json.decodeFromJsonElement<JustWatchDto>(
                deserializer = JustWatchDto.serializer(),
                element = jsonElement
            )



            Result.success(justWatch)

        } catch (e: ClientRequestException) {
            Log.d(
                "test??",
                e.toString()
            )
            Result.failure(e)
        } catch (e: ServerResponseException) {
            Log.d(
                "test?",
                e.toString()
            )

            Result.failure(e)
        } catch (e: SerializationException) {
            Log.d(
                "?",
                e.toString()
            )

            Result.failure(e)
        } catch (e: Exception) {
            Log.d(
                "test???",
                e.toString()
            )

            Result.failure(e)
        }
    }

}



