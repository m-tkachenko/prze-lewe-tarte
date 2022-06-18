package app.salo.przelewetarte.presentation.theme.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import app.salo.przelewetarte.presentation.theme.Shapes
import app.salo.przelewetarte.presentation.theme.Typography

private val DarkColorPalette = darkColors(
    primary = Green,
    primaryVariant = DarkGreen,
    secondary = Orange
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = DarkGreen,
    secondary = Orange,
    background = Background,
    onPrimary = LightGreen,
    onSecondary = LightOrange,
    error = Error

    /* Other default colors to override
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun PrzelewetarteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}