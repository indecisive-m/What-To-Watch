package com.example.whattowatch.presentation.search_results.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.ui.theme.WhatToWatchTheme

@Composable
fun ItemCard(
    item: Media,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    when (item) {
        is Movie -> Movie(
            item,
            onClick
        )

        is Tv -> {}
        else -> Unit
    }

}


@Composable
fun Movie(
    item: Movie,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 150.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.posterPath)
                .crossfade(enable = true)
                .build(),
            contentDescription = null,
            error = rememberVectorPainter(Icons.Default.Movie),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(150.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(8.dp)),
            placeholder = rememberVectorPainter(Icons.Default.Movie)
        )

        Spacer(Modifier.width(8.dp))
        Column() {
            Text(
                text = item.title.toString(),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier.height(4.dp))
            Text(
                text = item.overview.toString(),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    WhatToWatchTheme {
        ItemCard(
            Movie(
                adult = false,
                backdropPath = "/Ar7QuJ7sJEiC0oP3I8fKBKIQD9u.jpg",
                genreIds = listOf(
                    28,
                    18,
                    12
                ),
                id = 98,
                language = "en",
                originalTitle = "Gladiator",
                overview = "After the death of Emperor Marcus Aurelius, his devious son takes power and demotes Maximus, one of Rome's most capable generals who Marcus preferred. Eventually, Maximus is forced to become a gladiator and battle to the death against other men for the amusement of paying audiences.",
                popularity = 26.827,
                posterPath = "/ty8TGRuvJLPUmAR1H1nRIsgwvim.jpg",
                releaseDate = "2000-05-04",
                title = "Gladiator",
                video = false,
                averageVote = 8.219,
                voteCount = 19656
            ),
            onClick = {}
        )
    }
}