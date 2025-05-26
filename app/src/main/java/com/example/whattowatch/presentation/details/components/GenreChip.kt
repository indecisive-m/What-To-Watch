package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.whattowatch.domain.model.Genres

@Composable
fun GenreChip(
    genre: Genres,
    modifier: Modifier = Modifier
) {


    AssistChip(
        onClick = {},
        modifier = modifier.padding(horizontal = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = AssistChipDefaults.assistChipColors(
            containerColor = Color.LightGray,
            labelColor = Color.Black
        ),
        border = null,
//        elevation = TODO(),

        label = {
            Text(
                text = genre.name,
                style = MaterialTheme.typography.labelLarge
            )

        }
    )

}