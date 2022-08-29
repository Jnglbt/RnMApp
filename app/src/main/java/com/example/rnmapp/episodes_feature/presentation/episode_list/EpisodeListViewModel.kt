package com.example.rnmapp.episodes_feature.presentation.episode_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.toCharacter
import com.example.rnmapp.characters_feature.domain.use_cases.UseCases
import com.example.rnmapp.core.utils.Resource
import com.example.rnmapp.episodes_feature.data.sources.remote.dto.toEpisode
import com.example.rnmapp.episodes_feature.domain.use_cases.EpisodeUseCases
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class EpisodeListViewModel @Inject constructor(
    private val useCases: EpisodeUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(EpisodeListState())
    val state: StateFlow<EpisodeListState> get() = _state

    init {

        getEpisodes(null)
    }

    fun onEvent(event: EpisodeListEvents) {
        when(event) {
            is EpisodeListEvents.EnteredValue -> {
                _state.value = state.value.copy(
                    text = event.value
                )
            }
            is EpisodeListEvents.ChangeValueFocus -> {
                _state.value = state.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            state.value.text.isEmpty()
                )
            }
        }
    }

    fun getEpisodes(pageNumber: Int?) {
        println(state.value.isNavigationVisible)
        useCases.getEpisodesUseCase(pageNumber).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = EpisodeListState(
                        episodes = result.data!!.results.map { it.toEpisode() },
                        next = result.data.info.next,
                        previous = result.data.info.prev,
                    )
                }
                is Resource.Error -> {
                    _state.value = EpisodeListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = EpisodeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getNextPage() {
        if (state.value.next != null) {
            useCases.getNextEpisodePageUseCase(state.value.next!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = EpisodeListState(
                            episodes = result.data!!.results.map { it.toEpisode() },
                            next = result.data.info.next,
                            previous = result.data.info.prev
                        )
                    }
                    is Resource.Error -> {
                        _state.value = EpisodeListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = EpisodeListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun getPreviousPage() {
        if (state.value.previous != null) {
            useCases.getPreviousEpisodePageUseCase(state.value.previous!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = EpisodeListState(
                            episodes = result.data!!.results.map { it.toEpisode() },
                            previous = result.data.info.prev,
                            next = result.data.info.next,
                        )
                    }
                    is Resource.Error -> {
                        _state.value = EpisodeListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = EpisodeListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getEpisodeByCode(episode: String) {
        if (episode == "") {
            println("Get episodes")
            getEpisodes(null)
        }
        useCases.getEpisodeByCodeUseCase(episode).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = EpisodeListState(
                        episodes = result.data!!.results.map { it.toEpisode() },
                        isNavigationVisible = false
                    )
                }
                is Resource.Error -> {
                    _state.value = EpisodeListState(
                        error = result.message ?: "An unexpected error occurred",
                        isNavigationVisible = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = EpisodeListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}