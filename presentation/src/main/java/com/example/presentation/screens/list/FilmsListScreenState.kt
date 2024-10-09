package com.example.presentation.screens.list

import com.example.presentation.models.ErrorUi
import com.example.presentation.models.FilmUi

sealed class FilmsListScreenState {
    data class Loading(
        val films: List<FilmUi> = emptyList(),
        val genres: List<String> = emptyList(),
        val selectedGenre: String = ""
    ) : FilmsListScreenState()

    data class Error(
        val films: List<FilmUi> = emptyList(),
        val genres: List<String> = emptyList(),
        val selectedGenre: String = "",
        val error: ErrorUi
    ) : FilmsListScreenState()

    data class Show(
        val films: List<FilmUi> = emptyList(),
        val genres: List<String> = emptyList(),
        val selectedGenre: String = "",
    ) : FilmsListScreenState()
}