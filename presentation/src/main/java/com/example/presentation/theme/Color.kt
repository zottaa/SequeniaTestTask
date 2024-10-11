package com.example.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColorScheme(
    val black: Color,
    val orange: Color,
    val grey: Color,
    val white: Color,
    val darkBlue: Color,
    val lightGrey1: Color,
    val lightGrey2: Color
)

internal val Colors = ColorScheme(
    black = Color(0xFF000000),
    darkBlue = Color(0xFF0E3165),
    grey = Color(0xFF4B4B4B),
    orange = Color(0xFFFFC967),
    white = Color(0xFFFFFFFF),
    lightGrey1 = Color(0xFFEFEFEF),
    lightGrey2 = Color(0xFFCBCBCB)
)

internal val LocalColorScheme = staticCompositionLocalOf {
    Colors
}