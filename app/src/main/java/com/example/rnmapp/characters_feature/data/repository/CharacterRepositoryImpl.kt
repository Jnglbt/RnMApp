package com.example.rnmapp.characters_feature.data.repository

import android.util.Log
import com.example.rnmapp.api.RickAndMortyApi
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterDto
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.CharacterResponse
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(pageNumber: Int?): CharacterResponse {
        return api.getCharacters(pageNumber)
    }

    override suspend fun getCharacterById(characterId: String): CharacterDto {
        Log.d("repository", characterId)
        return api.getCharacterById(characterId)
    }

    override suspend fun getNextPage(url: String): CharacterResponse {
        return api.getNextPage(url)
    }

    override suspend fun getPreviousPage(url: String): CharacterResponse {
        return api.getPreviousPage(url)
    }

    override suspend fun getCharactersByName(name: String): CharacterResponse {
        return api.getCharactersByName(name)
    }

    override suspend fun getCharacterByUrl(url: String): CharacterDto {
        return api.getCharacterByUrl(url)
    }
}