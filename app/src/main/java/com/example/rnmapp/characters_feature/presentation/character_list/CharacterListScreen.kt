package com.example.rnmapp.characters_feature.presentation.character_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rnmapp.characters_feature.presentation.character_list.components.CharacterListItem
import com.example.rnmapp.core.presentation.main_screen.components.SearchField
import com.example.rnmapp.core.presentation.main_screen.navigation.BottomNavItems

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharacterListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchField(
            text = state.value.text,
            hint = state.value.hint,
            isHintVisible = state.value.isHintVisible,
            onValueChange = { viewModel.onEvent(CharacterListEvents.EnteredValue(it)) },
            onFocusChange = { viewModel.onEvent(CharacterListEvents.ChangeValueFocus(it)) }
        )

        Button(
            onClick = {
                viewModel.getCharacterByName(state.value.text)
                focusManager.clearFocus()
            }
        ) {
            Text(text = "Search")
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    items(state.value.characters) { character ->
                        CharacterListItem(
                            character = character,
                            onItemClick = {
                                navController.navigate(BottomNavItems.CharacterDetailScreen.route + "/${character.id}")
                            }
                        )
                    }
                    if (state.value.isNavigationVisible) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(onClick = { viewModel.getPreviousPage() }) {
                                    Text(text = "Previous Page")
                                }
                                Button(onClick = { viewModel.getNextPage() }) {
                                    Text(text = "Next Page")
                                }
                            }
                        }
                    }
                }
            }

            if (state.value.error.isNotBlank()) {
                Text(
                    text = state.value.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}