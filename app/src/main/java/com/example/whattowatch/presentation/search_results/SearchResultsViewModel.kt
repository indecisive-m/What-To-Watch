package com.example.whattowatch.presentation.search_results

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.whattowatch.app.Route
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.MediaType
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
    private val mediaType = savedStateHandle.toRoute<Route.MediaList>().mediaType


    init {
        onSearchClick(query, mediaType)
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
                onSearchClick(query = action.query, action.mediaType)

            }

            is SearchResultsAction.OnSearchClear -> {
                _state.update {
                    it.copy(
                        searchQuery = "",
                        searchResultsStatus = Status.SUCCESS
                    )
                }
            }

            is SearchResultsAction.OnSearchOptionClick -> {
                _state.update {
                    it.copy(
                        mediaType = action.mediaType
                    )
                }
            }


            is SearchResultsAction.OnSearchResultsClear -> {
                _state.update {
                    it.copy(
                        searchResults = emptyList(),
                        searchQuery = "",
                        searchResultsStatus = Status.SUCCESS
                    )
                }
            }
        }
    }


    private fun onSearchClick(query: String, mediaType: MediaType) = viewModelScope.launch {

        if (state.value.searchQuery.isBlank()) {
            null
        }
        when (mediaType) {
            MediaType.MOVIE -> {

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
                                errorMessage = null,
                                mediaType = MediaType.MOVIE

                            )
                        }

                    }


                    .onFailure { exception ->

                        _state.update {
                            it.copy(
                                searchResults = emptyList(),
                                searchResultsStatus = Status.ERROR,
                                errorMessage = exception.message
                            )
                        }

                    }
            }

            MediaType.TV -> {
                _state.update {
                    it.copy(searchResultsStatus = Status.LOADING)
                }

                repository.searchTv(query)
                    .onSuccess { searchResults ->

                        Log.d(
                            "tvvm",
                            searchResults.toString()
                        )

                        _state.update {
                            it.copy(
                                searchResults = searchResults,
                                searchQuery = query,
                                searchResultsStatus = Status.SUCCESS,
                                errorMessage = null,
                                mediaType = MediaType.TV


                            )
                        }
                    }
                    .onFailure { exception ->
                        _state.update {
                            it.copy(
                                searchResults = emptyList(),
                                searchResultsStatus = Status.ERROR,
                                errorMessage = exception.message
                            )
                        }

                    }

            }
        }
    }

}


