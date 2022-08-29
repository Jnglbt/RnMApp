package com.example.rnmapp.characters_feature.domain.use_cases.get_character

import android.util.Log
import com.example.rnmapp.characters_feature.data.sources.remote.dto.character.toCharacter
import com.example.rnmapp.characters_feature.domain.models.Character
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(characterId: String): Flow<Resource<Character>> = flow {
        try {
            emit(Resource.Loading<Character>())
            val character = repository.getCharacterById(characterId).toCharacter()
            Log.d("Get Character", character.id.toString())
            emit(Resource.Success<Character>(character))
        } catch(e: HttpException) {
            emit(Resource.Error<Character>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            emit(Resource.Error<Character>("Couldn't reach server. Check your internet connection."))
        }
    }
}