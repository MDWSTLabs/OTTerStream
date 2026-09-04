package com.mdwst.otterstream.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// OTTerStream Color Palette
object OTTerStreamColors {
    val Black = Color(0xFF0A0E27)
    val DarkCharcoal = Color(0xFF1A1F3A)
    val Purple = Color(0xFF9D4EDD)
    val PurpleLight = Color(0xFFBB86FC)
    val PurpleAccent = Color(0xFF7209B7)
    val White = Color(0xFFFFFFFF)
    val Gray = Color(0xFF888888)
    val GrayDark = Color(0xFF444444)
}

private val DarkColorScheme = darkColorScheme(
    primary = OTTerStreamColors.Purple,
    onPrimary = OTTerStreamColors.Black,
    primaryContainer = OTTerStreamColors.PurpleAccent,
    onPrimaryContainer = OTTerStreamColors.PurpleLight,
    secondary = OTTerStreamColors.PurpleLight,
    onSecondary = OTTerStreamColors.Black,
    tertiary = OTTerStreamColors.Gray,
    background = OTTerStreamColors.Black,
    onBackground = OTTerStreamColors.White,
    surface = OTTerStreamColors.DarkCharcoal,
    onSurface = OTTerStreamColors.White,
    error = Color(0xFFCF6679),
    onError = Color(0xFF000000),
)

@Composable
fun OTTerStreamTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = OTTerStreamTypography,
        content = content
    )
}
