package com.example.rnmapp.core.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.rnmapp.core.presentation.main_screen.navigation.BottomNav
import com.example.rnmapp.core.presentation.main_screen.navigation.NavigationGraph

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNav(navController = navController) }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).padding(horizontal = 16.dp, vertical = 8.dp)) {
            NavigationGraph(navController = navController)
        }
    }
}