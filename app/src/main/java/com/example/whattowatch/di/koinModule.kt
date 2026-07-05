package com.example.whattowatch.di

import androidx.room.Room
import com.example.whattowatch.data.local.database.FavouritesDatabase
import com.example.whattowatch.data.local.storage.ImageStorage
import com.example.whattowatch.data.remote.network.HttpClientFactory
import com.example.whattowatch.data.remote.network.KtorRemoteDataSource
import com.example.whattowatch.data.remote.network.RemoteDataSource
import com.example.whattowatch.data.repository.DefaultMediaRepository
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.presentation.details.DetailsScreenViewModel
import com.example.whattowatch.presentation.home.HomeScreenViewModel
import com.example.whattowatch.presentation.search_results.SearchResultsViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {
    single { HttpClientFactory.create(OkHttp.create()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    singleOf(::DefaultMediaRepository).bind<MediaRepository>()


    single { ImageStorage(androidContext()) }


    single {
        Room.databaseBuilder(
            androidContext(),
            FavouritesDatabase::class.java,
            "favourites.db"
        ).build()
    }

    single { get<FavouritesDatabase>().favouritesDao() }
    viewModelOf(::SearchResultsViewModel)
    viewModelOf(::DetailsScreenViewModel)
    viewModelOf(::HomeScreenViewModel)
}