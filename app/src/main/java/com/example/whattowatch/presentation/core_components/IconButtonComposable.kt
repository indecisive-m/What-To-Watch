package com.example.whattowatch.presentation.core_components

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

@Composable
fun IconButtonComposable(
    icon: ImageVector,
    contentDescription: Int,
    tint: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = backgroundColor,
                shape = CircleShape
            )
            .background(backgroundColor)
    ) {

        Icon(
            imageVector = icon,
            contentDescription = stringResource(contentDescription),
            tint = tint,
            modifier = Modifier.size(24.dp)

        )
    }
}