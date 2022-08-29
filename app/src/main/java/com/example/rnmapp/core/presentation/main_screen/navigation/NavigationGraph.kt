package com.example.rnmapp.core.presentation.main_screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rnmapp.characters_feature.presentation.character_detail.CharacterDetailScreen
import com.example.rnmapp.characters_feature.presentation.character_list.CharacterListScreen
import com.example.rnmapp.episodes_feature.presentation.episode_detail.EpisodeDetailScreen
import com.example.rnmapp.episodes_feature.presentation.episode_list.EpisodeListScreen
import com.example.rnmapp.location_feature.presentation.location_detail.LocationDetailScreen
import com.example.rnmapp.location_feature.presentation.location_list.LocationListScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItems.CharacterListScreen.route) {
        composable(BottomNavItems.CharacterListScreen.route) {
            CharacterListScreen(navController = navController)
        }
        composable(BottomNavItems.EpisodeListScreen.route) {
            EpisodeListScreen(navController = navController)
        }
        composable(BottomNavItems.LocationListScreen.route) {
            LocationListScreen(navController = navController)
        }
        composable(BottomNavItems.CharacterDetailScreen.route + "/{characterId}") {
            CharacterDetailScreen(navController)
        }
        composable(BottomNavItems.EpisodeDetailScreen.route + "/{episodeId}") {
            EpisodeDetailScreen(navController)
        }
        composable(BottomNavItems.LocationDetailScreen.route + "/{locationId}") {
            LocationDetailScreen(navController)
        }
    }
}