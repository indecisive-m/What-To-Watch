package com.example.whattowatch.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.MediaType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val repository: MediaRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        loadUpcomingMovieData()
        fetchWaterLater()
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


    private fun loadUpcomingMovieData() = viewModelScope.launch {
        _state.update {
            it.copy(status = Status.LOADING)
        }

        repository.getUpcomingMovies()
            .onSuccess { upcomingMovies ->

                _state.update {
                    it.copy(
                        upcomingMovies = upcomingMovies,
                        status = Status.SUCCESS,
                        mediaType = MediaType.MOVIE
                    )
                }
            }
            .onFailure { exception ->

                _state.update {
                    it.copy(
                        status = Status.ERROR,
                        errorMessage = exception.message
                    )
                }
            }
    }


    private fun fetchWaterLater() = viewModelScope.launch {
        repository.getAllWatchLater()
            .collect { watchLater ->
                _state.update {
                    it.copy(
                        watchLater = watchLater
                    )
                }
            }

    }

}



