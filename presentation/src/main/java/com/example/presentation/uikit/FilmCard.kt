package com.example.presentation.uikit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.presentation.R
import com.example.presentation.models.FilmUi
import com.example.presentation.theme.LocalColorScheme
import com.example.presentation.theme.LocalTypography

@Composable
fun FilmCard(
    film: FilmUi,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val isPortrait = screenHeight > screenWidth
    val colors = LocalColorScheme.current
    val typography = LocalTypography.current

    val imageHeight = if (isPortrait) {
        296.dp
    } else {
        592.dp
    }

    Column(
        modifier = modifier.padding(paddingValues).clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .clip(RoundedCornerShape(4.dp)),
            model = film.imageUrl,
            contentDescription = stringResource(R.string.film_image),
            contentScale = ContentScale.Crop,
            error = {
                Box(
                    modifier = Modifier
                        .background(colors.lightGrey1)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.default_image_icon),
                        contentDescription = stringResource(
                            R.string.default_image_icon
                        ),
                        tint = colors.lightGrey2
                    )
                }
            },
            loading = {
                Box(
                    modifier = Modifier
                        .background(colors.lightGrey1)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.default_image_icon),
                        contentDescription = stringResource(
                            R.string.default_image_icon
                        ),
                        tint = colors.lightGrey2
                    )
                }
            }
        )
        Text(
            text = film.localizedName,
            style = typography.title3,
            color = colors.black,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun FilmCardPreview() {
    val film = FilmUi(
        id = 0L,
        localizedName = "Гренландия",
        name = "Гренландия",
        year = 1999,
        rating = 5.333f,
        description = "",
        genres = emptyList(),
        imageUrl = ""
    )
    Column {
        FilmCard(film = film) {

        }
    }
}