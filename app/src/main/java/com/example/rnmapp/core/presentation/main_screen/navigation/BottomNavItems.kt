package com.example.rnmapp.core.presentation.main_screen.navigation

sealed class BottomNavItems(val title: String, val route: String) {
    object CharacterListScreen: BottomNavItems("Characters","character_list_screen")
    object EpisodeListScreen: BottomNavItems("Episodes","episode_list_screen")
    object LocationListScreen: BottomNavItems("Locations","location_list_screen")
    object CharacterDetailScreen: BottomNavItems("Character detail","character_detail_screen")
    object EpisodeDetailScreen: BottomNavItems("Episode detail","episode_detail_screen")
    object LocationDetailScreen: BottomNavItems("Location detail","location_detail_screen")
}
