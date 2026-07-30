package com.example.whattowatch.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.whattowatch.app.Route
import com.example.whattowatch.domain.MediaRepository
import com.example.whattowatch.domain.MediaType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DetailsScreenViewModel(
    private val repository: MediaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    private val mediaId = savedStateHandle.toRoute<Route.MediaDetails>().id
    private val mediaType = savedStateHandle.toRoute<Route.MediaDetails>().mediaType


    init {

        viewModelScope.launch {
            supervisorScope {
                getMediaDetails(mediaId, mediaType)
                checkIfInWatchLater()
                getJustWatch(mediaId, mediaType)
            }
        }

    }


    fun onAction(action: DetailsScreenAction) {
        when (action) {
            DetailsScreenAction.OnFavouriteClick -> {

                viewModelScope.launch {
                    if (state.value.isWatchLater) {
                        repository.deleteFromWatchLater(mediaId, state.value.mediaType)
                    } else {
                        state.value.media?.let { media ->
                            repository.addToWatchLater(media)
                        }
                    }
                }
            }
        }
    }


    private suspend fun getMediaDetails(mediaId: Int, mediaType: MediaType) {

        when (mediaType) {
            MediaType.MOVIE -> {

                _state.update {
                    it.copy(status = Status.LOADING)
                }

                repository.getMovieDetails(mediaId)
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

            MediaType.TV -> {
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

    private fun checkIfInWatchLater() {
        repository.isMediaInWatchLater(mediaId)
            .onEach { isWatchLater ->
                _state.update {
                    it.copy(isWatchLater = isWatchLater)
                }
            }
            .launchIn(viewModelScope)

    }

    private suspend fun getJustWatch(mediaId: Int, mediaType: MediaType) {
        repository.getJustWatch(mediaId, mediaType)
            .onSuccess { results ->
                _state.update {
                    it.copy(
                        justWatch = results
                    )
                }
            }
            .onFailure {
                _state.update {
                    it.copy(
                        justWatch = null
                    )
                }
            }
    }

}

