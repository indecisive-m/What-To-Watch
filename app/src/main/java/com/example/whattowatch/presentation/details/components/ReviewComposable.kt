package com.example.whattowatch.presentation.details.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.whattowatch.R
import com.example.whattowatch.domain.model.Review

@Composable
fun ReviewComposable(
    review: Review,
    modifier: Modifier = Modifier
) {

    var isExpanded by remember {
        mutableStateOf("")
    }
    Card(
        border = null,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier
            .clickable(
                onClick = {
                    isExpanded = if (isExpanded == review.id) "" else review.id
                }
            )
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()

            ) {
                Column(
                    Modifier.width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = review.author,
                        style = MaterialTheme.typography.titleMedium,
                        letterSpacing = TextUnit(1.2F, TextUnitType.Sp),
                        color = MaterialTheme.colorScheme.onSecondary,
                    )
                    HorizontalDivider(
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
                Icon(
                    imageVector =
                        if (isExpanded == review.id) Icons.Outlined.ArrowDropDown else Icons.AutoMirrored.Default.ArrowRight,
                    contentDescription = stringResource(R.string.read_more),
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)

                )


            }

            Spacer(Modifier.height(8.dp))
            Text(
                text = review.content,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isExpanded == review.id) Int.MAX_VALUE else 3,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }


    }


}



