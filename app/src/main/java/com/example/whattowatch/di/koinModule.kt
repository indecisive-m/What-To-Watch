package com.example.whattowatch.di

import com.example.whattowatch.data.network.HttpClientFactory
import com.example.whattowatch.data.network.KtorRemoteDataSource
import com.example.whattowatch.data.network.RemoteDataSource
import com.example.whattowatch.data.repository.DefaultMediaRepository
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.presentation.details.DetailsScreenViewModel
import com.example.whattowatch.presentation.search_results.SearchResultsViewModel
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val koinModule = module {
    single { HttpClientFactory.create(OkHttp.create()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    singleOf(::DefaultMediaRepository).bind<MediaRepository>()

    viewModelOf(::SearchResultsViewModel)
    viewModelOf(::DetailsScreenViewModel)
}