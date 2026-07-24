package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.whattowatch.domain.model.Cast
import com.example.whattowatch.domain.model.ProductionCompanies


@Composable
fun InfoTabRow(
    cast: List<Cast>,
    productionCompanies: List<ProductionCompanies>,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableIntStateOf(0) }
    val titles = listOf("Cast", "Production Companies")
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SecondaryTabRow(
            selectedTabIndex = state,
            containerColor = MaterialTheme.colorScheme.secondary,
            divider = {
                HorizontalDivider(
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.outline
                )
            },
            modifier = Modifier.clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))

        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.secondary,
                    text = {
                        Text(
                            text = title,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    },
                )
            }
        }

        if (state == 0) {
            CastRow(
                cast = cast,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .background(MaterialTheme.colorScheme.surface)
            )
        } else {
            ProductionCompaniesRow(
                productionCompanies = productionCompanies,
                modifier = Modifier.padding(vertical = 16.dp)

            )
        }
    }
}



