package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.model.Cast

@Composable
fun CastRow(
    cast: List<Cast>,
    modifier: Modifier = Modifier
) {


    Column {
        Text(
            text = "Cast:",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxWidth()


        ) {
            items(cast) { person ->

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(person.profilePath)
                            .crossfade(enable = true)
                            .build(),
                        contentDescription = person.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .aspectRatio(2f / 3f)


                    )
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(8.dp)

                    ) {
                        Text(
                            text = person.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = person.character,
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }

                }
            }
        }


    }
}

