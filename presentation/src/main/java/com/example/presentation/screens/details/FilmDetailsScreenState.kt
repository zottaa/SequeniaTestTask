package com.example.presentation.screens.details

import com.example.presentation.models.FilmUi

internal sealed class FilmDetailsScreenState {
    data object Loading : FilmDetailsScreenState()
    data class Show(val film: FilmUi) : FilmDetailsScreenState()
}