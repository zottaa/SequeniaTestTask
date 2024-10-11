package com.example.domain

import com.example.domain.models.Film
import com.example.domain.models.FilmsLoadStatus
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {
    fun films(): Flow<FilmsLoadStatus>

    fun film(id: Long): Flow<Film>
}