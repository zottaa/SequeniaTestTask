package com.example.presentation.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.Colors
import com.example.presentation.theme.Typography

fun LazyListScope.genresList(
    genres: List<String>,
    selectedGenre: String? = null,
    paddingValues: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
    colors: GenresListColors = GenresListColors(),
    itemTextStyle: TextStyle = Typography.genre,
    onGenreClick: (String) -> Unit,
) {
    items(genres) { genre ->
        Box(
            modifier = Modifier
                .background(
                    if (genre == selectedGenre) {
                        colors.selectedItemContainerColor
                    } else {
                        Color.Transparent
                    }
                )
                .clickable {
                    onGenreClick(genre)
                }
                .padding(paddingValues)
                .fillMaxWidth()

        ) {
            Text(text = genre.replaceFirstChar { char -> char.uppercaseChar() }, style = itemTextStyle, color = colors.contentColor)
        }
    }
}

@Immutable
data class GenresListColors(
    val selectedItemContainerColor: Color = Colors.orange,
    val contentColor: Color = Colors.black
)

@Preview(showBackground = true)
@Composable
fun PreviewGenresList() {
    val genres = listOf(
        "Биография",
        "Боевик",
        "Детектив",
        "Драма",
        "Комедия",
        "Криминал",
        "Мелодрама",
        "Мюзикл",
        "Приключения",
        "Триллер",
        "Ужасы",
        "Фантастика",
    )
    LazyColumn {
        genresList(
            genres = genres,
            selectedGenre = "Мелодрама",
            onGenreClick = {

            }
        )
    }
}


