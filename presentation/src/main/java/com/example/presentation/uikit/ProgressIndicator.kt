package com.example.presentation.uikit

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.LocalColorScheme

@Composable
internal fun ProgressIndicator(modifier: Modifier = Modifier) {
    val colors = LocalColorScheme.current
    CircularProgressIndicator(modifier = modifier, color = colors.orange, strokeWidth = 4.36.dp)
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ProgressIndicatorPreview() {
    ProgressIndicator(modifier = Modifier.size(48.dp))
}