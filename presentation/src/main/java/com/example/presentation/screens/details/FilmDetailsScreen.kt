package com.example.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.presentation.R
import com.example.presentation.models.FilmUi
import com.example.presentation.theme.LocalColorScheme
import com.example.presentation.theme.LocalTypography
import com.example.presentation.uikit.ProgressIndicator
import com.example.presentation.uikit.TopBar
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun FilmDetailsScreen(navigateBack: () -> Unit) {
    FilmDetailsScreen(navigateBack = navigateBack, viewModel = koinViewModel())
}


@Composable
internal fun FilmDetailsScreen(
    viewModel: FilmDetailsViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    val state by viewModel.getState().collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBar(
                title = when (val currentState = state) {
                    FilmDetailsScreenState.Loading -> ""
                    is FilmDetailsScreenState.Show -> currentState.film.name
                }, canGoBack = true
            ) {
                navigateBack()
            }
        }
    ) { innerPadding ->
        when (val currentState = state) {
            FilmDetailsScreenState.Loading -> LoadingContent()
            is FilmDetailsScreenState.Show -> FilmDetailsContent(
                film = currentState.film,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp, bottom = 15.dp)
            )
        }
    }
}

@Composable
private fun LoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        ProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
internal fun FilmDetailsContent(film: FilmUi, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val defaultModifier =
        Modifier
            .size(width = 176.dp, height = 268.dp)
            .clip(RoundedCornerShape(4.dp))
    Column(modifier = modifier.verticalScroll(scrollState)) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .then(defaultModifier)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop,
            model = film.imageUrl,
            contentDescription = stringResource(R.string.film_image),
            error = {
                DefaultImage(
                    modifier = defaultModifier
                )
            },
            loading = {
                DefaultImage(
                    modifier = defaultModifier
                )
            }
        )
        FilmTitle(film.localizedName)
        FilmGenresAndYear(film)
        FilmRating(film.rating)
        FilmDescription(film.description)
    }
}

@Composable
private fun FilmTitle(title: String) {
    val typography = LocalTypography.current
    val colors = LocalColorScheme.current

    Text(
        text = title,
        style = typography.title1,
        color = colors.black,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
private fun FilmGenresAndYear(film: FilmUi) {
    val typography = LocalTypography.current
    val colors = LocalColorScheme.current

    Text(
        text = if (film.genres.isNotEmpty()) {
            stringResource(
                R.string.genres_and_year,
                film.genres.joinToString(", ").plus(","),
                film.year
            )
        } else {
            stringResource(R.string.year, film.year)
        },
        style = typography.genre,
        color = colors.grey,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
private fun FilmRating(rating: Float) {
    val typography = LocalTypography.current
    val colors = LocalColorScheme.current

    if (rating > 0) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 14.dp)
        ) {
            Text(
                text = rating.toString(),
                style = typography.rating,
                color = colors.darkBlue
            )
            Text(
                text = stringResource(R.string.rating_from),
                style = typography.ratingFrom,
                color = colors.darkBlue,
                modifier = Modifier.padding(top = 9.dp, bottom = 3.dp)
            )
        }
    } else {
        Text(
            text = stringResource(R.string.no_rating),
            style = typography.rating,
            color = colors.darkBlue,
            modifier = Modifier.padding(bottom = 14.dp)
        )
    }
}

@Composable
private fun FilmDescription(description: String) {
    val typography = LocalTypography.current
    val colors = LocalColorScheme.current

    if (description.isNotBlank()) {
        Text(
            text = description,
            style = typography.text,
            color = colors.black,
            modifier = Modifier.padding(bottom = 14.dp)
        )
    } else {
        Text(
            text = stringResource(R.string.no_description),
            style = typography.text,
            color = colors.black,
            modifier = Modifier.padding(bottom = 14.dp)
        )
    }
}


@Composable
private fun DefaultImage(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.default_image),
        contentDescription = stringResource(id = R.string.default_image),
        contentScale = ContentScale.Crop
    )
}