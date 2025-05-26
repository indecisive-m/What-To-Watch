package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.whattowatch.R

@Composable
fun IconButtonComposable(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.Black.copy(alpha = 0.8f),
                shape = CircleShape
            )
            .background(Color.Black.copy(alpha = 0.7f))
    ) {

        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.go_back),
            tint = Color.White,
            modifier = Modifier.size(24.dp)

        )
    }
}