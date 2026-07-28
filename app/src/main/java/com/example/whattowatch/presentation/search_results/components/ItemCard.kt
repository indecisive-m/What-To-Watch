package com.example.whattowatch.presentation.search_results.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.whattowatch.domain.Media
import com.example.whattowatch.domain.Movie
import com.example.whattowatch.domain.Tv

@Composable
fun ItemCard(
    item: Media,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 150.dp)
            .clickable(onClick = onClick)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer),
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
                .clip(RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)),
            placeholder = rememberVectorPainter(Icons.Default.Movie)
        )

        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(
                text = when (item) {
                    is Movie -> item.title.toString()
                    is Tv -> item.name.toString()
                    else -> ""
                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(modifier.height(4.dp))
            Text(
                text = item.overview,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

        }
    }
}






