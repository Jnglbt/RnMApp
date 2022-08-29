package com.example.rnmapp.characters_feature.presentation.character_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnmapp.characters_feature.domain.use_cases.get_character.GetCharacterUseCase
import com.example.rnmapp.core.domain.use_cases.get_episode_by_url.GetEpisodeByUrlUseCase
import com.example.rnmapp.core.utils.Constants.PARAM_CHARACTER_ID
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.domain.models.Episode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getEpisodeByUrlUseCase: GetEpisodeByUrlUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    val state: State<CharacterDetailState> = _state

    private val episodes = mutableListOf<Episode?>()

    init {
        Log.d("ViewModel", "Inside init")
        savedStateHandle.get<String>(PARAM_CHARACTER_ID)?.let { characterId ->
            getCharacter(characterId)
        }
    }

    private fun getCharacter(characterId: String) {
        Log.d("ViewModel", characterId)
        getCharacterUseCase(characterId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    getEpisodes(result.data!!.episode)
                    _state.value = CharacterDetailState(
                        character = result.data,
                        episodes = episodes
                    )
                }
                is Resource.Error -> {
                    _state.value = CharacterDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CharacterDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getEpisodes(urls: List<String>) {
        runBlocking {
            urls.forEach { url ->
                getEpisodeByUrlUseCase(url).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            println("Result ${result.data?.episode}")
                            episodes.add(result.data)
                        }
                        is Resource.Error -> {
                            _state.value = CharacterDetailState(
                                error = result.message ?: "An unexpected error occurred"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = CharacterDetailState(isLoading = true)
                        }
                    }

                }

//                    .onEach { result ->
//                    when (result) {
//                        is Resource.Success -> {
//                            println("Result ${result.data?.episode}")
//                            episodes.add(result.data)
//                        }
//                        is Resource.Error -> {
//                            _state.value = CharacterDetailState(
//                                error = result.message ?: "An unexpected error occurred"
//                            )
//                        }
//                        is Resource.Loading -> {
//                            _state.value = CharacterDetailState(isLoading = true)
//                        }
//                    }
//                }.launchIn(viewModelScope)
            }
        }
    }
}