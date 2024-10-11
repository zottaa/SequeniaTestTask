package com.example.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

internal val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

internal val TopBar = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp
)

internal val Title1 = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 26.sp
)

internal val Title2 = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp
)

internal val Title3 = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
)

internal val RatingFrom = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp
)

internal val Rating = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold,
    fontSize = 24.sp
)

internal val Text = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp
)

internal val Genre = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

internal val SnackBarText = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp
)

internal val SnackBarButton = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
)

@Immutable
internal data class CustomTypography(
    val topBar: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val ratingFrom: TextStyle,
    val rating: TextStyle,
    val text: TextStyle,
    val genre: TextStyle,
    val snackBarText: TextStyle,
    val snackBarButton: TextStyle
)

internal val Typography = CustomTypography(
    topBar = TopBar,
    title1 = Title1,
    title2 = Title2,
    title3 = Title3,
    ratingFrom = RatingFrom,
    rating = Rating,
    text = Text,
    genre = Genre,
    snackBarText = SnackBarText,
    snackBarButton = SnackBarButton
)

internal val LocalTypography = staticCompositionLocalOf {
    Typography
}