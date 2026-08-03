package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.model.Images

@Composable
fun ImageCarousel(
    images: List<Images>

) {

    if (images.isNotEmpty()) {

        LazyRow(
            state = rememberLazyListState(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier

        ) {
            items(images) { item ->


                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.filePath)
                        .crossfade(enable = true)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(150.dp)
                        .aspectRatio(item.aspectRatio.toFloat())
                        .clip(RoundedCornerShape(16.dp))

                )

            }
        }
    }
}
