package com.example.domain.models

import com.example.domain.error.DataError

sealed class FilmsLoadStatus {
    data class Success(val films: List<Film>) : FilmsLoadStatus()

    data class Loading(val films: List<Film>) : FilmsLoadStatus()

    data class Error(val films: List<Film>, val error: DataError) : FilmsLoadStatus()
}

