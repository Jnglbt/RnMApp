package com.example.rnmapp.characters_feature.presentation.character_list

import com.example.rnmapp.characters_feature.domain.models.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val next: String? = null,
    val previous: String? = null,
    val text: String = "",
    val hint: String = "Enter name",
    val isHintVisible: Boolean = true,
    val isNavigationVisible: Boolean = true,
    val error: String = ""
)
