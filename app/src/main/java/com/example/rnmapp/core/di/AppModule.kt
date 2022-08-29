package com.example.rnmapp.core.di

import com.example.rnmapp.api.RickAndMortyApi
import com.example.rnmapp.characters_feature.data.repository.CharacterRepositoryImpl
import com.example.rnmapp.characters_feature.domain.repository.CharacterRepository
import com.example.rnmapp.characters_feature.domain.use_cases.UseCases
import com.example.rnmapp.characters_feature.domain.use_cases.get_character.GetCharacterUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_characters.GetCharactersUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_characters_by_name.GetCharactersByNameUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_next_page.GetNextPageUseCase
import com.example.rnmapp.characters_feature.domain.use_cases.get_previous_page.GetPreviousPageUseCase
import com.example.rnmapp.core.utils.Constants.BASE_URL
import com.example.rnmapp.episodes_feature.data.repository.EpisodeRepositoryImpl
import com.example.rnmapp.episodes_feature.domain.repository.EpisodeRepository
import com.example.rnmapp.episodes_feature.domain.use_cases.EpisodeUseCases
import com.example.rnmapp.episodes_feature.domain.use_cases.get_episode.GetEpisodeUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_episode_by_code.GetEpisodeByCodeUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_episodes.GetEpisodesUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_next_page.GetNextEpisodePageUseCase
import com.example.rnmapp.episodes_feature.domain.use_cases.get_previous_page.GetPreviousEpisodePageUseCase
import com.example.rnmapp.location_feature.data.repository.LocationRepositoryImpl
import com.example.rnmapp.location_feature.domain.repository.LocationRepository
import com.example.rnmapp.location_feature.domain.use_cases.LocationUseCases
import com.example.rnmapp.location_feature.domain.use_cases.get_location.GetLocationUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_location_by_name.GetLocationByNameUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_locations.GetLocationsUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_next_page.GetNextLocationPageUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_previous_page.GetPreviousLocationPageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickAndMortyApi(): RickAndMortyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickAndMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickAndMortyApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideEpisodeRepository(api: RickAndMortyApi): EpisodeRepository {
        return EpisodeRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLocationRepository(api: RickAndMortyApi): LocationRepository {
        return LocationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: CharacterRepository): UseCases {
        return UseCases(
            getCharactersByNameUseCase = GetCharactersByNameUseCase(repository),
            getCharactersUseCase = GetCharactersUseCase(repository),
            getCharacterUseCase = GetCharacterUseCase(repository),
            getNextPageUseCase = GetNextPageUseCase(repository),
            getPreviousPageUseCase = GetPreviousPageUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideEpisodeUseCases(repository: EpisodeRepository): EpisodeUseCases {
        return EpisodeUseCases(
            getEpisodesUseCase = GetEpisodesUseCase(repository),
            getEpisodeUseCase = GetEpisodeUseCase(repository),
            getNextEpisodePageUseCase = GetNextEpisodePageUseCase(repository),
            getPreviousEpisodePageUseCase = GetPreviousEpisodePageUseCase(repository),
            getEpisodeByCodeUseCase = GetEpisodeByCodeUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(repository: LocationRepository): LocationUseCases {
        return LocationUseCases(
            getLocationByNameUseCase = GetLocationByNameUseCase(repository),
            getLocationsUseCase = GetLocationsUseCase(repository),
            getLocationUseCase = GetLocationUseCase(repository),
            getNextLocationPageUseCase = GetNextLocationPageUseCase(repository),
            getPreviousLocationPageUseCase = GetPreviousLocationPageUseCase(repository)
        )
    }
}