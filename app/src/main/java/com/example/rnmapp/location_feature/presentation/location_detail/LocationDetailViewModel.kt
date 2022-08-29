package com.example.rnmapp.location_feature.presentation.location_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.core.domain.use_cases.get_character_by_url.GetCharacterByUrlUseCase
import com.example.rnmapp.core.utils.Constants.PARAM_LOCATION_ID
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.location_feature.domain.use_cases.get_location.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class LocationDetailViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(LocationDetailState())
    val state: State<LocationDetailState> = _state

    private val residents = mutableListOf<Character?>()

    init {
        savedStateHandle.get<String>(PARAM_LOCATION_ID)?.let { locationId ->
            getLocation(locationId)
        }
    }

    private fun getLocation(locationId: String) {
        getLocationUseCase(locationId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getResidents(result.data!!.residents)
//                    delay(1000L)
                    _state.value = LocationDetailState(
                        location = result.data,
                        residents = residents
                    )
                    println("Get location done")
                }
                is Resource.Error -> {
                    _state.value = LocationDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = LocationDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getResidents(urls: List<String>) {
        runBlocking {
            urls.forEach { url ->
                getCharacterByUrlUseCase(url).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            residents.add(result.data)
                        }
                        is Resource.Error -> {
                            _state.value = LocationDetailState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = LocationDetailState(isLoading = true)
                        }
                    }
                }

//                    .onEach { result ->
//                    when (result) {
//                        is Resource.Success -> {
//                            residents.add(result.data)
//                        }
//                        is Resource.Error -> {
//                            _state.value = LocationDetailState(
//                                error = result.message ?: "An unexpected error occurred"
//                            )
//                        }
//                        is Resource.Loading -> {
//                            _state.value = LocationDetailState(isLoading = true)
//                        }
//                    }
//                }.launchIn(viewModelScope)
            }
        }
    }
}