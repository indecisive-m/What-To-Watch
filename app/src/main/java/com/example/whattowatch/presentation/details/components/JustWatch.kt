package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.R
import com.example.whattowatch.domain.model.JustWatch
import com.example.whattowatch.domain.model.JustWatchItem

@Composable
fun JustWatch(
    justWatch: JustWatch
) {


    if (justWatch.flatrate?.isNotEmpty() == true) {
        JustWatchRow(
            title = R.string.subscriptions,
            list = justWatch.flatrate
        )
    }
    if (justWatch.buy?.isNotEmpty() == true) {
        JustWatchRow(
            title = R.string.available_to_buy,
            list = justWatch.buy
        )
    }
    if (justWatch.rent?.isNotEmpty() == true) {
        JustWatchRow(
            title = R.string.available_to_rent,
            list = justWatch.rent
        )
    }
}


@Composable
fun JustWatchRow(
    title: Int,
    list: List<JustWatchItem>
) {
    Column(
        verticalArrangement = Arrangement.Center,

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {
        Column {
            Text(
                text = "${stringResource(title)}:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                letterSpacing = TextUnit(1.1F, TextUnitType.Sp),

                )
            Spacer(Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                items(list) { item ->
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
    justWatchItem: JustWatchItem,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(justWatchItem.logoPath)
            .crossfade(enable = true)
            .build(),
        contentDescription = justWatchItem.providerName,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(55.dp)
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f / 1f)


    )


}