package com.example.whattowatch.presentation.search_results.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whattowatch.R
import com.example.whattowatch.presentation.core_components.IconButtonComposable
import com.example.whattowatch.presentation.search_results.SearchResultsAction

@Composable
fun NavigationIconRow(
    onBackClick: () -> Unit,
    onAction: (SearchResultsAction) -> Unit,
    modifier: Modifier = Modifier

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)


    ) {
        IconButtonComposable(
            icon = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = R.string.go_back,
            tint = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
            onClick = onBackClick
        )
        IconButtonComposable(
            icon = Icons.Filled.Clear,
            contentDescription = R.string.clear,
            tint = MaterialTheme.colorScheme.onSurface,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
            onClick = { onAction(SearchResultsAction.OnSearchResultsClear) }
        )

    }
}