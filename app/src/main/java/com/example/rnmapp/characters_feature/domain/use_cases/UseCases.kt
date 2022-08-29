package com.example.rnmapp.characters_feature.domain.use_cases

import com.example.rnmapp.characters_feature.domain.use_cases.get_character.GetCharacterUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_characters.GetCharactersUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_characters_by_name.GetCharactersByNameUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_next_page.GetNextPageUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_previous_page.GetPreviousPageUseCase

data class UseCases(
    val getCharacterUseCase: GetCharacterUseCase,
    val getCharactersUseCase: GetCharactersUseCase,
    val getCharactersByNameUseCase: GetCharactersByNameUseCase,
    val getPreviousPageUseCase: GetPreviousPageUseCase,
    val getNextPageUseCase: GetNextPageUseCase
)