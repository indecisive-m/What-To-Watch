package com.example.whattowatch.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
        null
    }



    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "${text}: ",
            fontWeight = FontWeight.SemiBold
        )
        Text(textResult.toString())

    }

}


private fun convertToCurrency(number: Int): String {
    return NumberFormat.getCurrencyInstance(Locale.US)
        .format(number)
}
