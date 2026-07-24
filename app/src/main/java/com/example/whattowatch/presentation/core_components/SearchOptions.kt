package com.example.whattowatch.presentation.core_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        AssistChip(
            onClick = {
                onSearchOptionClick(
                    MediaType.MOVIE
                )

            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(1F),
            shape = AssistChipDefaults.shape,
            border = null,
            colors = AssistChipDefaults.assistChipColors(
                containerColor = if (searchOption == MediaType.MOVIE) MaterialTheme.colorScheme.primary else Color.Transparent,
                labelColor = if (searchOption == MediaType.MOVIE) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
            ),
            label = {
                Text(
                    text = "Movie",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        )
        AssistChip(
            onClick = {
                onSearchOptionClick(
                    MediaType.TV
                )

            },
            modifier = modifier
                .padding(16.dp)
                .weight(1F),
            shape = AssistChipDefaults.shape,
            border = null,
            colors = AssistChipDefaults.assistChipColors(
                containerColor = if (searchOption == MediaType.TV) MaterialTheme.colorScheme.primary else Color.Transparent,
                labelColor = if (searchOption == MediaType.TV) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
            ),
            label = {
                Text(
                    text = "Television",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        )
    }
}

@Composable
fun SearchOptionsTabRow(
    searchOption: MediaType,
    onSearchOptionClick: (MediaType) -> Unit,
    modifier: Modifier = Modifier
) {

    var state by remember {
        if (searchOption == MediaType.MOVIE) {
            mutableIntStateOf(0)
        } else {
            mutableIntStateOf(1)
        }

    }
    val titles = listOf(MediaType.MOVIE, MediaType.TV)


    PrimaryTabRow(
        selectedTabIndex = if (searchOption == MediaType.MOVIE) {
            0
        } else {
            1
        },
        divider = {},
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(
                    matchContentSize = false,
                    selectedTabIndex = if (searchOption == MediaType.MOVIE) {
                        0
                    } else {
                        1
                    },
                ),
                width = Dp.Unspecified,
                color = MaterialTheme.colorScheme.primary

            )
        },

        ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = state == index,
                onClick = {
                    state = index

                    if (title == MediaType.MOVIE) {
                        onSearchOptionClick(
                            MediaType.MOVIE
                        )
                    } else {
                        onSearchOptionClick(
                            MediaType.TV
                        )
                    }
                },
                title = title.toString()
            )
        }

    }
}

@Composable
fun Tab(
    selected: Boolean,
    onClick: () -> Unit,
    title: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clickable(
                onClick = onClick
            )
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
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