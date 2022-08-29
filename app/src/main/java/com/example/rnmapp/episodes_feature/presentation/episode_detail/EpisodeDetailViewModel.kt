package com.example.rnmapp.episodes_feature.presentation.episode_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.characters_feature.domain.use_cases.get_character.GetCharacterUseCase
import com.example.rnmapp.core.domain.use_cases.get_character_by_url.GetCharacterByUrlUseCase
import com.example.rnmapp.core.utils.Constants.PARAM_CHARACTER_ID
import com.example.rnmapp.core.utils.Constants.PARAM_EPISODE_ID
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.domain.use_cases.get_episode.GetEpisodeUseCase
import com.example.rnmapp.location_feature.presentation.location_detail.LocationDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailViewModel @Inject constructor(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    private val getCharacterByUrlUseCase: GetCharacterByUrlUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(EpisodeDetailState())
    val state: State<EpisodeDetailState> = _state

    private val characters = mutableListOf<Character?>()

    init {
        Log.d("ViewModel", "Inside init")
        savedStateHandle.get<String>(PARAM_EPISODE_ID)?.let { episodeId ->
            getEpisode(episodeId)
        }
    }

    private fun getEpisode(episodeId: String) {
        Log.d("ViewModel", episodeId)
        getEpisodeUseCase(episodeId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getCharacters(result.data!!.characters)
//                    delay(1000L)
                    _state.value = EpisodeDetailState(
                        episode = result.data,
                        characters = characters
                    )
                }
                is Resource.Error -> {
                    _state.value = EpisodeDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = EpisodeDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getCharacters(urls: List<String>) {
        runBlocking {
            urls.forEach { url ->
                getCharacterByUrlUseCase(url).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            characters.add(result.data)
                        }
                        is Resource.Error -> {
                            _state.value = EpisodeDetailState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = EpisodeDetailState(isLoading = true)
                        }
                    }
                }

//                onEach { result ->
//                    when (result) {
//                        is Resource.Success -> {
//                            characters.add(result.data)
//                        }
//                        is Resource.Error -> {
//                            _state.value = EpisodeDetailState(
//                                error = result.message ?: "An unexpected error occurred"
//                            )
//                        }
//                        is Resource.Loading -> {
//                            _state.value = EpisodeDetailState(isLoading = true)
//                        }
//                    }
//                }.launchIn(viewModelScope)
            }
        }
    }
}