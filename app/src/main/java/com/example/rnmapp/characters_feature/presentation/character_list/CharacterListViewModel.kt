package com.example.rnmapp.characters_feature.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.toCharacter
import com.example.rnmapp.characters_feature.domain.use_cases.UseCases
import com.example.rnmapp.core.utils.Resource
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalPagerApi::class)
class CharacterListViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterListState())
    val state: StateFlow<CharacterListState> get() = _state

    init {

        getCharacters(null)
    }

    fun onEvent(event: CharacterListEvents) {
        when(event) {
            is CharacterListEvents.EnteredValue -> {
                _state.value = state.value.copy(
                    text = event.value
                )
            }
            is CharacterListEvents.ChangeValueFocus -> {
                _state.value = state.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            state.value.text.isEmpty()
                )
            }
        }
    }

    private fun getCharacters(pageNumber: Int?) {
        println(state.value.isNavigationVisible)
        useCases.getCharactersUseCase(pageNumber).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterListState(
                        characters = result.data!!.results.map { it.toCharacter() },
                        next = result.data.info.next,
                        previous = result.data.info.prev,
                    )
                }
                is Resource.Error -> {
                    _state.value = CharacterListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CharacterListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getNextPage() {
        if (state.value.next != null) {
            useCases.getNextPageUseCase(state.value.next!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CharacterListState(
                            characters = result.data!!.results.map { it.toCharacter() },
                            next = result.data.info.next,
                            previous = result.data.info.prev
                        )
                    }
                    is Resource.Error -> {
                        _state.value = CharacterListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CharacterListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
    fun getPreviousPage() {
        if (state.value.previous != null) {
            useCases.getPreviousPageUseCase(state.value.previous!!).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CharacterListState(
                            characters = result.data!!.results.map { it.toCharacter() },
                            previous = result.data.info.prev,
                            next = result.data.info.next,
                        )
                    }
                    is Resource.Error -> {
                        _state.value = CharacterListState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = CharacterListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getCharacterByName(name: String) {
        if (name == "") {
            println("Get characters")
            getCharacters(null)
        }
        useCases.getCharactersByNameUseCase(name).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CharacterListState(
                        characters = result.data!!.results.map { it.toCharacter() },
                        isNavigationVisible = false
                    )
                }
                is Resource.Error -> {
                    _state.value = CharacterListState(
                        error = result.message ?: "An unexpected error occurred",
                        isNavigationVisible = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = CharacterListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}