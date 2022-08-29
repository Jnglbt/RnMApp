package com.example.rnmapp.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.rnmapp.core.presentation.main_screen.MainScreenView
import com.example.rnmapp.core.ui.theme.RnMAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RnMAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreenView()
                }
            }
        }

    }
}