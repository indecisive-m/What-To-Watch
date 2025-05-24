package com.example.whattowatch.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.domain.MediaRepository
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
        Log.d(
            "HomeScreenInit",
            "fetched"
        )
        loadUpcomingMovieData()
    }


    fun onAction(action: HomeScreenAction) {
        when (action) {
            is HomeScreenAction.OnSearchPress -> {

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
        }

    }


    private fun loadUpcomingMovieData() = viewModelScope.launch {
        _state.update {
            it.copy(status = Status.LOADING)
        }

        repository.getUpcomingMovies()
            .onSuccess { upcomingMovies ->


                Log.d(
                    "test",
                    upcomingMovies[0].toString()
                )
                _state.update {
                    it.copy(
                        upcomingMovies = upcomingMovies,
                        status = Status.SUCCESS
                    )
                }
            }
            .onFailure { exception ->

                Log.d(
                    "test",
                    exception.message.toString()
                )

                _state.update {
                    it.copy(
                        status = Status.ERROR,
                        errorMessage = exception.message
                    )
                }
            }
    }


    private fun onSearchPress(query: String) = viewModelScope.launch {


    }
}



