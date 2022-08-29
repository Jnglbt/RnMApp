package com.example.rnmapp.characters_feature.domain.repository

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterDto
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse

interface CharacterRepository {

    suspend fun getCharacters(pageNumber: Int?): CharacterResponse

    suspend fun  getCharacterById(characterId: String): CharacterDto

    suspend fun  getNextPage(url: String): CharacterResponse

    suspend fun getPreviousPage(url: String) : CharacterResponse

    suspend fun getCharactersByName(name: String): CharacterResponse

    suspend fun getCharacterByUrl(url: String) : CharacterDto
}