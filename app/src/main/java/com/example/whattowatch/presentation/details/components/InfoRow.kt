package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import java.text.NumberFormat
import java.util.Locale

@Composable
fun InfoRow(
    text: String,
    infoFromState: Int,
    modifier: Modifier = Modifier,
    isCurrency: Boolean = false,
    isRunTime: Boolean = false,

    ) {

    val textResult = if (isRunTime) {
        "$infoFromState minutes"
    } else if (isCurrency) {
        convertToCurrency(infoFromState)
    } else {
        infoFromState
    }



    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "${text}: ",
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary

        )
        Text(
            text = textResult.toString(),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )

    }

}


private fun convertToCurrency(number: Int): String {
    return NumberFormat.getCurrencyInstance(Locale.US)
        .format(number)
}
