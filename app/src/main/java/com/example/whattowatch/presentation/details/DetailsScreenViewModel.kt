package com.example.whattowatch.presentation.details

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

class DetailsScreenViewModel(
    private val repository: MediaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    private val mediaId = savedStateHandle.toRoute<Route.MediaDetails>().id


    init {
        Log.d(
            "Detailsvm",
            mediaId.toString()
        )

        getMediaDetails(mediaId)
    }

    private fun getMediaDetails(mediaId: Int) = viewModelScope.launch {

        when (state.value.mediaOption) {
            MediaOption.MOVIE -> {

                _state.update {
                    it.copy(status = Status.LOADING)
                }

                repository.getMovieDetails(mediaId)
                    .onSuccess { results ->

                        Log.d(
                            "Details",
                            results.toString()
                        )
                        _state.update {
                            it.copy(
                                status = Status.SUCCESS,
                                media = results
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

            MediaOption.TV -> {
                _state.update {
                    it.copy(status = Status.LOADING)
                }

                repository.getTvDetails(mediaId)
                    .onSuccess { results ->
                        _state.update {
                            it.copy(
                                status = Status.SUCCESS,
                                media = results
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