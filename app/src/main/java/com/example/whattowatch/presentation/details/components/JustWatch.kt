package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.model.JustWatch
import com.example.whattowatch.domain.model.JustWatchItem

@Composable
fun JustWatch(
    justWatch: JustWatch
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column {
            Text(
                text = "Subscription Services",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Spacer(Modifier.height(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                justWatch.flatrate?.forEach { item ->
                    SingleJustWatch(
                        item
                    )

                }
            }
        }
        Column {
            Text(
                text = "Available to Rent",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary
            )
            FlowRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                justWatch.rent?.forEach { item ->
                    SingleJustWatch(
                        item
                    )

                }
            }
        }
        Column {
            Text(
                text = "Available to Buy",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondary


            )
            FlowRow(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                justWatch.buy?.forEach { item ->
                    SingleJustWatch(
                        item
                    )

                }
            }
        }
    }
}


@Composable
fun SingleJustWatch(
    justWatchItem: JustWatchItem
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(justWatchItem.logoPath)
            .crossfade(enable = true)
            .build(),
        contentDescription = justWatchItem.providerName,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(75.dp)
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f / 1f)


    )


}