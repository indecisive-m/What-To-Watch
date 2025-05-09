package com.example.whattowatch.presentation.search_results.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.MovieDetails
import com.example.whattowatch.domain.Tv
import com.example.whattowatch.domain.TvDetails
import com.example.whattowatch.ui.theme.WhatToWatchTheme

@Composable
fun ItemCard(
    item: Media,
    modifier: Modifier = Modifier
) {


    when (item) {
        is Movie -> Movie(item)
        is MovieDetails -> {}
        is Tv -> {}
        is TvDetails -> {}
    }


}


@Composable
fun Movie(
    item: Movie,
    modifier: Modifier = Modifier
) {
    Row(
        modifier.fillMaxWidth()
    ) {
        Box(
            Modifier
                .background(Color.Red)
                .size(100.dp)
        )
        Column() {
            Text(text = item.title.toString(),
                 style = MaterialTheme.typography.titleLarge,
                 fontWeight = FontWeight.Bold
            )

            Spacer(modifier.height(4.dp))
            Text(text = item.overview.toString(),
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
            )
        )
    }
}