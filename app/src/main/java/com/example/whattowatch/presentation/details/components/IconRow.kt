package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.whattowatch.R
import com.example.whattowatch.presentation.details.DetailsScreenAction

@Composable
fun IconRow(
    onBackClick: () -> Unit,
    onAction: (DetailsScreenAction) -> Unit,
    isFavourite: Boolean,
    modifier: Modifier = Modifier

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,

        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(8.dp)


    ) {
        IconButtonComposable(
            icon = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = R.string.go_back,
            tint = Color.White,
            onClick = onBackClick
        )
        IconButtonComposable(
            icon = if (isFavourite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = if (isFavourite) R.string.remove_from_favourites else R.string.add_to_favourites,
            tint = if (isFavourite) Color.Red else Color.White,
            onClick = { onAction(DetailsScreenAction.OnFavouriteClick) }
        )

    }
}