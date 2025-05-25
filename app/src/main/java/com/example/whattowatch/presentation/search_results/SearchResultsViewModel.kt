package com.example.whattowatch.presentation.search_results

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.whattowatch.app.Route
import com.example.whattowatch.domain.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchResultsViewModel(
    private val repository: MediaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(SearchResultsState())
    val state = _state.asStateFlow()

    private val query = savedStateHandle.toRoute<Route.MediaList>().searchQuery


    init {
        onSearchClick(query)
    }

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

            is SearchResultsAction.OnSearchClick -> {
                onSearchClick(query = action.query)

            }

            is SearchResultsAction.OnSearchClear -> {
                _state.update {
                    it.copy(
                        searchQuery = "",
                        searchResultsStatus = Status.IDLE
                    )
                }
            }


            is SearchResultsAction.OnSearchResultsClear -> {
                _state.update {
                    it.copy(
                        searchResults = emptyList(),
                        searchQuery = "",
                        searchResultsStatus = Status.IDLE
                    )
                }
            }
        }
    }


    private fun onSearchClick(query: String) = viewModelScope.launch {

        if (state.value.searchQuery.isBlank()) {
            null
        }
        when (state.value.searchOption) {
            SearchOption.MOVIE -> {

                _state.update {
                    it.copy(searchResultsStatus = Status.LOADING)
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
                                searchQuery = query,
                                searchResultsStatus = Status.SUCCESS,
                                errorMessage = null

                            )
                        }

                    }


                    .onFailure { exception ->

                        _state.update {
                            it.copy(
                                searchResultsStatus = Status.ERROR,
                                errorMessage = exception.message
                            )
                        }

                    }
            }

            SearchOption.TV -> {
                _state.update {
                    it.copy(searchResultsStatus = Status.LOADING)
                }

                repository.searchTv(query)
                    .onSuccess { searchResults ->
                        _state.update {
                            it.copy(
                                searchResults = searchResults,
                                searchResultsStatus = Status.SUCCESS,
                                errorMessage = null


                            )
                        }
                    }
                    .onFailure {
                        _state.update {
                            it.copy(
                                searchResultsStatus = Status.ERROR
                            )
                        }

                    }

            }
        }
    }

}


