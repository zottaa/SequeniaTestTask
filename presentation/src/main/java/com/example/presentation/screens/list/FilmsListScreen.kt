package com.example.presentation.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.presentation.R
import com.example.presentation.models.FilmUi
import com.example.presentation.theme.LocalColorScheme
import com.example.presentation.theme.LocalTypography
import com.example.presentation.uikit.FilmCard
import com.example.presentation.uikit.ProgressIndicator
import com.example.presentation.uikit.TopBar
import com.example.presentation.uikit.genresList
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilmsListScreen() {
    FilmsListScreen(viewModel = koinViewModel())
}

@Composable
internal fun FilmsListScreen(viewModel: FilmsListViewModel = koinViewModel()) {
    val state by viewModel.getState().collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val colors = LocalColorScheme.current

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    shape = RoundedCornerShape(2.dp),
                    actionColor = colors.orange,
                    contentColor = colors.white,
                )
            }
        },
        topBar = {
            TopBar(title = stringResource(id = R.string.films))
        },
    ) { innerPadding ->
        when (val currentState = state) {
            is FilmsListScreenState.Error -> {
                val actionLabel = context.getString(R.string.repeat).uppercase()
                LaunchedEffect(currentState.error.id) {
                    scope.launch {
                        val result = snackBarHostState.showSnackbar(
                            message = currentState.error.message,
                            actionLabel = actionLabel,
                        )
                        when (result) {
                            SnackbarResult.Dismissed -> {

                            }

                            SnackbarResult.ActionPerformed -> {
                                viewModel.refresh()
                            }
                        }
                    }
                }
                FilmsListContent(
                    modifier = Modifier.padding(innerPadding),
                    films = currentState.films,
                    genres = currentState.genres,
                    selectedGenre = currentState.selectedGenre,
                    onGenreClick = viewModel::selectGenre
                )
            }

            is FilmsListScreenState.Loading -> LoadingContent(
                modifier = Modifier.padding(innerPadding),
                films = currentState.films,
                genres = currentState.genres,
                selectedGenre = currentState.selectedGenre,
                onGenreClick = viewModel::selectGenre
            )

            is FilmsListScreenState.Show -> FilmsListContent(
                modifier = Modifier.padding(innerPadding),
                films = currentState.films,
                genres = currentState.genres,
                selectedGenre = currentState.selectedGenre,
                onGenreClick = viewModel::selectGenre
            )
        }
    }
}

@Composable
internal fun LoadingContent(
    modifier: Modifier = Modifier,
    films: List<FilmUi>,
    genres: List<String>,
    selectedGenre: String,
    onGenreClick: (String) -> Unit
) {
    Box(modifier = modifier.fillMaxSize()) {
        ProgressIndicator(
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
        FilmsListContent(
            films = films,
            genres = genres,
            selectedGenre = selectedGenre,
            onGenreClick = onGenreClick
        )
    }
}

@Composable
internal fun FilmsListContent(
    modifier: Modifier = Modifier,
    films: List<FilmUi>,
    genres: List<String>,
    selectedGenre: String,
    onGenreClick: (String) -> Unit
) {
    val typography = LocalTypography.current
    val colors = LocalColorScheme.current

    LazyColumn(
        modifier = modifier,
    ) {
        item {
            Spacer(modifier = Modifier.size(8.dp))

            if (genres.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.genres),
                    style = typography.title2,
                    color = colors.black,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }

        genresList(genres, selectedGenre.ifBlank { null }, onGenreClick = onGenreClick)

        item {
            Spacer(modifier = Modifier.size(16.dp))
            if (films.isNotEmpty()) {
                Text(
                    text = stringResource(R.string.films),
                    style = typography.title2,
                    color = colors.black,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
        items(films.chunked(2)) { filmPair ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            ) {
                FilmCard(
                    film = filmPair[0],
                    modifier = Modifier.weight(0.5f)
                )
                Spacer(modifier = Modifier.size(8.dp))
                if (filmPair.size > 1) {
                    FilmCard(
                        film = filmPair[1],
                        modifier = Modifier.weight(0.5f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(0.5f))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}






