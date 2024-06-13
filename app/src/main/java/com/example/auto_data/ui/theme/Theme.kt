package com.example.auto_data.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF830900),
    secondary = Color(0x9E2196F3),
    tertiary = Color(0xFFE91E63),
    background = Color(0xFFACACAC),
    surface = Color(0x80FFFBFE),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF1C1B1F),
    onTertiary = Color(0x0FA23A3A),
    onBackground = Color(0xB4000000),
    onSurface = Color(0xFF1C1B1F),
    onPrimaryContainer = Color(0xFF000000),
    onSurfaceVariant = Color(0xFF000000)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xDDDB0F00),
    secondary = Color(0xFF0037FF),
    tertiary = Color(0xFFE91E63),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFF0037FF),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFF1C1B1F),
    onTertiary = Color(0x0FA23A3A),
    onBackground = Color(0xB4000000),
    onSurface = Color(0xFF1C1B1F),
    onPrimaryContainer = Color(0xFF000000),
    onSurfaceVariant = Color(0xFF000000)

)

@Composable
fun AutoDataTheme(
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


    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}