package com.example.rnmapp.core.domain.use_cases.get_character_by_url

import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.toCharacter
import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterByUrlUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(url: String): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading<Character>())
            val character = repository.getCharacterByUrl(url).toCharacter()
            emit(Resource.Success<Character>(character))
        } catch(e: HttpException) {
            emit(Resource.Error<Character>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<Character>("Couldn't reach server. Check your internet connection."))
        }
    }
}