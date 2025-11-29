package julio.koffi.daklu.bumayeapp.ui.theme

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


// Schéma de couleurs de l'application
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF81C784),
    secondary = Color(0xFF4FC3F7),
    tertiary = Color(0xFFFFB74D),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    error = Color(0xFFCF6679),
    onPrimary = Color(0xFF000000),
    onSecondary = Color(0xFF000000),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
    onSurface = Color(0xFFFFFFFF),
    onError = Color(0xFF000000)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50),
    secondary = Color(0xFF03A9F4),
    tertiary = Color(0xFFFF9800),
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    error = Color(0xFFB00020),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    onError = Color(0xFFFFFFFF)
)

// Fonction de composition du thème
@Composable
fun BumayeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    //Couleurs dynamiques disponibles sur Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val _v_colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val _v_context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(_v_context) else dynamicLightColorScheme(_v_context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val _v_view = LocalView.current
    if (!_v_view.isInEditMode) {
        SideEffect {
            val _v_window = (_v_view.context as Activity).window
            _v_window.statusBarColor = _v_colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(_v_window, _v_view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = _v_colorScheme,
        typography = BumayeTypography,
        content = content
    )
}