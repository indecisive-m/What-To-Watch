package com.example.whattowatch.presentation.core_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whattowatch.domain.MediaType
import com.example.whattowatch.ui.theme.WhatToWatchTheme

@Composable
fun SearchOptions(
    searchOption: MediaType,
    onSearchOptionClick: (MediaType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

        ) {
        AssistChip(
            onClick = {
                onSearchOptionClick(
                    MediaType.MOVIE
                )

            },
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = AssistChipDefaults.assistChipColors(
                containerColor = if (searchOption == MediaType.MOVIE) Color.LightGray else Color.Transparent,
                labelColor = Color.Black
            ),
            label = {
                Text(
                    text = "Movie",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
        AssistChip(
            onClick = {
                onSearchOptionClick(
                    MediaType.TV
                )

            },
            modifier = modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = AssistChipDefaults.assistChipColors(
                containerColor = if (searchOption == MediaType.TV) Color.LightGray else Color.Transparent,
                labelColor = Color.Black
            ),
            label = {
                Text(
                    text = "Television",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SearchOptionsPreview() {
    WhatToWatchTheme {
        SearchOptions(
            searchOption = MediaType.MOVIE,
            onSearchOptionClick = {}
        )
    }
}