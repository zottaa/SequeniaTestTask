package com.example.presentation.uikit

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kotlin.math.sqrt

@Composable
fun FilmCard(
    film: FilmUi,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    onClick: () -> Unit
) {
    val colors = LocalColorScheme.current
    val typography = LocalTypography.current
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val screenDiagonal = sqrt(
        (configuration.screenWidthDp * configuration.screenWidthDp +
                configuration.screenHeightDp * configuration.screenHeightDp).toDouble()
    ).dp

    val imageHeight = if (isPortrait) {
        screenDiagonal * 0.28f
    } else {
        screenDiagonal * 0.65f
    }
    val imageModifier = Modifier
        .fillMaxSize()
        .height(imageHeight)
        .clip(RoundedCornerShape(4.dp))

    Column(
        modifier = modifier
            .padding(paddingValues)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = imageModifier,
            model = film.imageUrl,
            contentDescription = stringResource(R.string.film_image),
            contentScale = ContentScale.Crop,
            error = {
                DefaultImage(imageModifier)
            },
            loading = {
                DefaultImage(imageModifier)
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

@Composable
private fun DefaultImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.default_image),
        contentDescription = stringResource(
            R.string.default_image
        ),
        contentScale = ContentScale.Crop
    )
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