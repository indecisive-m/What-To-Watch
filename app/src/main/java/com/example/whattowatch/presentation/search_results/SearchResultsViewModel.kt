package com.example.whattowatch.presentation.search_results

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.domain.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchResultsViewModel(
    private val repository: MediaRepository
) : ViewModel() {
    private val _state = MutableStateFlow(SearchResultsState())
    val state = _state.asStateFlow()


    fun onAction(action: SearchResultsAction) {
        when (action) {
            is SearchResultsAction.OnItemClick -> {

            }

            is SearchResultsAction.OnSearchQueryChange -> {

                _state.update {
                    it.copy(
                        searchQuery = action.query

                    )
                }

            }

            is SearchResultsAction.OnSearchPress -> {
                onSearchPress(query = action.query)

            }

            is SearchResultsAction.OnSearchClear -> {
                _state.update {
                    it.copy(searchQuery = "")
                }
            }

            is SearchResultsAction.LoadUpcomingMovieData -> {
                loadUpcomingMovieData()
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
                    upcomingMovies.toString()
                )
                _state.update {
                    it.copy(
                        searchResults = upcomingMovies,
                        status = Status.SUCCESS
                    )
                }
            }
            .onFailure { exception ->

                Log.d(
                    "testBitches",
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

        if (state.value.searchQuery.isBlank()) {
            null
        }
        when (state.value.searchOption) {
            SearchOption.MOVIE -> {

                _state.update {
                    it.copy(status = Status.LOADING)
                }

                repository.searchMovies(query)
                    .onSuccess { searchResults ->

                        Log.d(
                            "test",
                            searchResults.toString()
                        )

                        _state.update {
                            it.copy(
                                searchResults = searchResults,
                                status = Status.SUCCESS

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

            SearchOption.TV -> {
                _state.update {
                    it.copy(status = Status.LOADING)
                }

                repository.searchTv(query)
                    .onSuccess { searchResults ->
                        _state.update {
                            it.copy(
                                searchResults = searchResults,
                                status = Status.SUCCESS

                            )
                        }
                    }
                    .onFailure {
                        _state.update {
                            it.copy(
                                status = Status.ERROR
                            )
                        }

                    }

            }
        }
    }

}


