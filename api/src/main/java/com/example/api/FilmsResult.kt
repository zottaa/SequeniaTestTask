package com.example.api

import com.example.api.models.FilmDTO

sealed class FilmsResult {
    data class Success(
        val films: List<FilmDTO>
    ) : FilmsResult()

    data class Error(
        val exception: Exception
    ) : FilmsResult()
}