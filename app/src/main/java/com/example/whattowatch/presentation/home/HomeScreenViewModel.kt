package com.example.whattowatch.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.MediaType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class HomeScreenViewModel(
    private val repository: MediaRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(status = Status.LOADING)
            }

            supervisorScope {
                loadUpcomingMovieData()
                loadPopularMovieData()
                loadTopRatedMovies()
                loadNowPlayingMovies()
                loadPopularTvShows()
                loadTopRatedTvShows()
            }

            _state.update { currentState ->
                val allFetchCallsFailed = currentState.upcomingMoviesError != null &&
                        currentState.popularMoviesError != null &&
                        currentState.topRatedMoviesError != null &&
                        currentState.nowPlayingMoviesError != null &&
                        currentState.topRatedTvShowsError != null &&
                        currentState.popularTvShowsError != null

                if (allFetchCallsFailed) {
                    currentState.copy(
                        errorMessage = "Could not load any data",
                        status = Status.ERROR
                    )
                } else {
                    currentState.copy(status = Status.SUCCESS)

                }

            }

        }
    }


    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnSearchClick -> {

            }

            is HomeScreenAction.OnItemClick -> {

            }

            is HomeScreenAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }

            is HomeScreenAction.OnSearchClear -> {
                _state.update {
                    it.copy(
                        searchQuery = ""
                    )
                }
            }

            is HomeScreenAction.OnSearchOptionClick -> {
                _state.update {
                    it.copy(
                        mediaType = action.mediaType
                    )
                }
            }

            HomeScreenAction.OnSeeMoreButtonClick -> {

            }
        }

    }


    private suspend fun loadUpcomingMovieData() {


        repository.getUpcomingMovies()
            .onSuccess { upcomingMovies ->

                _state.update {
                    it.copy(
                        upcomingMovies = upcomingMovies,
                        mediaType = MediaType.MOVIE
                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        upcomingMoviesError = exception.message
                    )
                }
            }


    }

    private suspend fun loadPopularMovieData() {

        repository.getPopularMovies()
            .onSuccess { popularMovies ->

                _state.update {
                    it.copy(
                        popularMovies = popularMovies,
                        mediaType = MediaType.MOVIE

                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        popularMoviesError = exception.message
                    )
                }
            }
    }


    private suspend fun loadTopRatedMovies() {


        repository.getTopRatedMovies()
            .onSuccess { topRated ->

                _state.update {
                    it.copy(
                        topRatedMovies = topRated,
                        mediaType = MediaType.MOVIE

                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        topRatedMoviesError = exception.message
                    )
                }
            }
    }

    private suspend fun loadNowPlayingMovies() {

        repository.getNowPlayingMovies()
            .onSuccess { nowPlaying ->

                _state.update {
                    it.copy(
                        nowPlayingMovies = nowPlaying,
                        mediaType = MediaType.MOVIE

                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        nowPlayingMoviesError = exception.message
                    )
                }
            }
    }

    private suspend fun loadPopularTvShows() {

        repository.getPopularTvShows()
            .onSuccess { popularTv ->

                _state.update {
                    it.copy(
                        popularTvShows = popularTv,
                        mediaType = MediaType.TV

                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        popularTvShowsError = exception.message
                    )
                }
            }
    }

    private suspend fun loadTopRatedTvShows() {

        repository.getTopRatedTvShows()
            .onSuccess { topRated ->

                _state.update {
                    it.copy(
                        topRatedTvShows = topRated,
                        mediaType = MediaType.TV

                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        topRatedTvShowsError = exception.message
                    )
                }
            }
    }


}




