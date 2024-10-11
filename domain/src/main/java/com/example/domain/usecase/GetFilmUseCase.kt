package com.example.domain.usecase

import com.example.domain.models.Film
import kotlinx.coroutines.flow.Flow

interface GetFilmUseCase {
    operator fun invoke(): Flow<Film>
}
