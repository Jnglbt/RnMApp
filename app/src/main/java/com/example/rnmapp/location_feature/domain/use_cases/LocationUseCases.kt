package com.example.rnmapp.location_feature.domain.use_cases

import com.example.rnmapp.location_feature.domain.use_cases.get_location.GetLocationUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_location_by_name.GetLocationByNameUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_locations.GetLocationsUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_next_page.GetNextLocationPageUseCase
import com.example.rnmapp.location_feature.domain.use_cases.get_previous_page.GetPreviousLocationPageUseCase


data class LocationUseCases(
    val getLocationUseCase: GetLocationUseCase,
    val getLocationsUseCase: GetLocationsUseCase,
    val getNextLocationPageUseCase: GetNextLocationPageUseCase,
    val getPreviousLocationPageUseCase: GetPreviousLocationPageUseCase,
    val getLocationByNameUseCase: GetLocationByNameUseCase
)
