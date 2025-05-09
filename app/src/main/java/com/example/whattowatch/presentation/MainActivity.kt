package com.example.whattowatch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.whattowatch.presentation.search_results.SearchResultsScreenRoot
import com.example.whattowatch.ui.theme.WhatToWatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WhatToWatchTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SearchResultsScreenRoot(
                    )

                }
            }
        }
    }
}


