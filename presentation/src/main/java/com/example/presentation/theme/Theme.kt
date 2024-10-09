package com.example.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


@Composable
fun SequeniaTestTaskTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTypography provides Typography,
        LocalColorScheme provides Colors
    ) {
        MaterialTheme(
            content = content
        )
    }
}