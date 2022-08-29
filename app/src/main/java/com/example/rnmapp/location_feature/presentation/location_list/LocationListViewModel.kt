package com.example.rnmapp.location_feature.presentation.location_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.location_feature.data.sources.remote.dto.toLocation
import com.example.rnmapp.location_feature.domain.use_cases.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class LocationListViewModel @Inject constructor(
    private val useCases: LocationUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(LocationListState())
    val state: StateFlow<LocationListState> get() = _state

    init {

        getLocations(null)
    }

    fun onEvent(event: LocationListEvents) {
        when(event) {
            is LocationListEvents.EnteredValue -> {
                _state.value = state.value.copy(
                    text = event.value
                )
            }
            is LocationListEvents.ChangeValueFocus -> {
                _state.value = state.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            state.value.text.isEmpty()
                )
            }
        }
    }

    private fun getLocations(pageNumber: Int?) {
        useCases.getLocationsUseCase(pageNumber).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LocationListState(
                        locations = result.data!!.results.map { it.toLocation() },
                        next = result.data.info.next,
                        previous = result.data.info.prev,
                    )
                }
                is Resource.Error -> {
                    _state.value = LocationListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = LocationListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getNextPage() {
        if (state.value.next != null) {
            useCases.getNextLocationPageUseCase(state.value.next!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = LocationListState(
                            locations = result.data!!.results.map { it.toLocation() },
                            next = result.data.info.next,
                            previous = result.data.info.prev
                        )
                    }
                    is Resource.Error -> {
                        _state.value = LocationListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = LocationListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun getPreviousPage() {
        if (state.value.previous != null) {
            useCases.getPreviousLocationPageUseCase(state.value.previous!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = LocationListState(
                            locations = result.data!!.results.map { it.toLocation()},
                            previous = result.data.info.prev,
                            next = result.data.info.next,
                        )
                    }
                    is Resource.Error -> {
                        _state.value = LocationListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = LocationListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getLocationByName(name: String) {
        if (name == "") {
            println("Get episodes")
            getLocations(null)
        }
        useCases.getLocationByNameUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LocationListState(
                        locations = result.data!!.results.map { it.toLocation() },
                        isNavigationVisible = false
                    )
                }
                is Resource.Error -> {
                    _state.value = LocationListState(
                        error = result.message ?: "An unexpected error occurred",
                        isNavigationVisible = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = LocationListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}