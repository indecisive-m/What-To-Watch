package com.example.whattowatch.presentation.watch_later

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattowatch.domain.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WatchLaterViewModel(
    private val repository: MediaRepository,

    ) : ViewModel() {
    private val _state = MutableStateFlow(WatchLaterState())
    val state = _state.asStateFlow()

    init {
        fetchWaterLater()
    }


    fun onAction(action: WatchScreenAction) {
        when (action) {
            is WatchScreenAction.OnItemClick -> {

            }
            
        }
    }

    private fun fetchWaterLater() = viewModelScope.launch {
        repository.getAllWatchLater()
            .collect { watchLater ->
                _state.update {
                    it.copy(
                        watchLaterItems = watchLater
                    )
                }
            }

    }
}

