package com.example.task2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val FormColorScheme = darkColorScheme(
    primary = CoralAccent,
    onPrimary = DeepNavy,
    secondary = MintGlow,
    onSecondary = DeepNavy,
    background = DeepNavy,
    onBackground = CreamText,
    surface = CardSurface,
    onSurface = CreamText,
    outline = InputBorder,
    error = ErrorGlow
)

@Composable
fun Task2Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = FormColorScheme,
        typography = Typography,
        content = content
    )
}
