package com.example.whattowatch.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Color(0xFF080D24),
    primary = Color(0xFFD4AF37),
    onPrimary = Color(0xFF1C1300),
    secondary = Color(0xFF244C56),
    onSecondary = Color(0xFFE3F0F3),
    surfaceContainer = Color(0xFF222C50),
    onSurface = Color(0xFFE3F0F3).copy(alpha = 0.75F),
    outline = Color(0xFFD4AF37).copy(alpha = 0.75F),
    surfaceTint = Color(0xFFD4AF37), // Icon Colour
)

private val LightColorScheme = lightColorScheme(

    background = Color(0xFF080D24),
    primary = Color(0xFFD4AF37),
    onPrimary = Color(0xFF1C1300),
    secondary = Color(0xFF244C56),
    onSecondary = Color(0xFFE3F0F3),
    surfaceContainer = Color(0xFF222C50),
    onSurface = Color(0xFFE3F0F3).copy(alpha = 0.75F),
    surfaceTint = Color(0xFFD4AF37) // Icon Colour


    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun WhatToWatchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}