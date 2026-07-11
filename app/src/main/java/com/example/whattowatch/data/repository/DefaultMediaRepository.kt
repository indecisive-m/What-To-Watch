package com.example.whattowatch.data.repository

import android.util.Log
import com.example.whattowatch.data.local.database.FavouritesDao
import com.example.whattowatch.data.local.storage.ImageStorage
import com.example.whattowatch.data.remote.dto.movie_search.MovieSearchResultsDto
import com.example.whattowatch.data.remote.dto.tv_search.TvSearchResultsDto
import com.example.whattowatch.data.remote.mappers.toFavouritesEntity
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DefaultMediaRepository(
    private val remoteDataSource: KtorRemoteDataSource,
    private val favouritesDao: FavouritesDao,
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

    override fun getAllFavourites(): Flow<List<Media>> {
        return favouritesDao.getAllFavourites()
            .map { favouritesEntities ->
                favouritesEntities.map { it -> it.toMedia() }
            }
    }

    override suspend fun addToFavourites(media: Media) {

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

        return favouritesDao.addToFavourites(entity)
    }

    override fun isBookFavourited(id: Int): Flow<Boolean> {
        return favouritesDao.getAllFavourites()
            .map { favouritesEntities ->
                favouritesEntities.any { it.id == id }
            }
    }


    override suspend fun deleteFromFavorites(id: Int, mediaType: MediaType) {


        imageStorage.deleteFile(mediaType, id)

        return favouritesDao.removeFromFavourites(id)
    }
}






