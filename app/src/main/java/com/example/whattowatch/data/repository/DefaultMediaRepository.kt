package com.example.whattowatch.data.repository

import android.util.Log
import com.example.whattowatch.data.local.database.WatchLaterDao
import com.example.whattowatch.data.local.storage.ImageStorage
import com.example.whattowatch.data.remote.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.remote.dto.tv_search.TvSearchResultsDto
import com.example.whattowatch.data.remote.mappers.helpers.toImages
import com.example.whattowatch.data.remote.mappers.toFavouritesEntity
import com.example.whattowatch.data.remote.mappers.toJustWatch
import com.example.whattowatch.data.remote.mappers.toMedia
import com.example.whattowatch.data.remote.mappers.toMovie
import com.example.whattowatch.data.remote.mappers.toMovieDetails
import com.example.whattowatch.data.remote.mappers.toTv
import com.example.whattowatch.data.remote.mappers.toTvDetails
import com.example.whattowatch.data.remote.network.KtorRemoteDataSource
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.MovieDetails
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.TvDetails
import com.example.whattowatch.domain.model.Images
import com.example.whattowatch.domain.model.JustWatch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DefaultMediaRepository(
    private val remoteDataSource: KtorRemoteDataSource,
    private val watchLaterDao: WatchLaterDao,
    private val imageStorage: ImageStorage
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
            .map { dto: TvSearchResultsDto ->
                dto.results.map {
                    it.toTv()
                }
            }
    }

    override suspend fun getMovieDetails(id: Int): Result<MovieDetails> {
        return remoteDataSource.getMovieDetails(id)
            .map { it.toMovieDetails() }

    }

    override suspend fun getTvDetails(id: Int): Result<TvDetails> {
        return remoteDataSource.getTvDetails(id)
            .map { it.toTvDetails() }
    }


    override suspend fun getUpcomingMovies(): Result<List<Movie>> {
        return remoteDataSource.getUpcomingMovies()
            .map { dto ->
                dto.results.map {
                    it.toMovie()
                }
            }

    }

    override suspend fun getNowPlayingMovies(): Result<List<Movie>> {
        return remoteDataSource.getNowPlayingMovies()
            .map { dto ->
                dto.results.map {
                    it.toMovie()
                }
            }
    }

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return remoteDataSource.getPopularMovies()
            .map { dto ->
                dto.results.map {
                    it.toMovie()
                }
            }
    }

    override suspend fun getTopRatedMovies(): Result<List<Movie>> {
        return remoteDataSource.getTopRatedMovies()
            .map { dto ->
                dto.results.map {
                    it.toMovie()
                }
            }
    }

    override suspend fun getTopRatedTvShows(): Result<List<Tv>> {
        return remoteDataSource.getTopRatedTvShows()
            .map { dto ->
                dto.results.map {
                    it.toTv()
                }
            }
    }

    override suspend fun getPopularTvShows(): Result<List<Tv>> {
        return remoteDataSource.getPopularTvShows()
            .map { dto ->
                dto.results.map {
                    it.toTv()
                }
            }
    }

    override suspend fun getImages(id: Int, mediaType: MediaType): Result<List<Images>> {
        return remoteDataSource.getImages(id, mediaType)
            .map { dto ->
                dto.map {
                    it.toImages()
                }
            }
    }

    override fun getAllWatchLater(): Flow<List<Media>> {
        return watchLaterDao.getAllWatchLater()
            .map { watchLaterEntities ->
                watchLaterEntities.map { it -> it.toMedia() }
            }
    }

    override suspend fun addToWatchLater(media: Media) {

        val mediaToMediaEntity = media.toFavouritesEntity()

        val imageBytes = remoteDataSource.getImage(media.posterPath)


        imageBytes.onSuccess { bytes ->
            imageStorage.saveImageToStorage(
                mediaType = mediaToMediaEntity.mediaType,
                mediaId = mediaToMediaEntity.id,
                bytes = bytes
            )
        }


        val localLink = remoteDataSource.getImage(media.posterPath)
            .onFailure { error ->
                Log.e("Image", "Failed to get image", error)
            }
            .getOrNull()
            ?.let { bytes ->
                imageStorage.saveImageToStorage(
                    mediaType = mediaToMediaEntity.mediaType,
                    mediaId = mediaToMediaEntity.id,
                    bytes = bytes
                )
            }

        val entity = media.toFavouritesEntity().copy(
            imageLink = localLink
        )

        return watchLaterDao.addToWatchLater(entity)
    }

    override fun isMediaInWatchLater(id: Int): Flow<Boolean> {
        return watchLaterDao.getAllWatchLater()
            .map { watchLaterEntities ->
                watchLaterEntities.any { it.id == id }
            }
    }


    override suspend fun deleteFromWatchLater(id: Int, mediaType: MediaType) {


        imageStorage.deleteFile(mediaType, id)

        return watchLaterDao.removeFromWatchLater(id)
    }

    override suspend fun getJustWatch(id: Int, mediaType: MediaType): Result<JustWatch> {
        return remoteDataSource.getJustWatch(id, mediaType)
            .map { it.toJustWatch() }
    }

}






