package com.example.domain.usecase

import com.example.domain.models.FilmsLoadStatus
import kotlinx.coroutines.flow.Flow

interface GetFilmsUseCase {
    operator fun invoke(): Flow<FilmsLoadStatus>
}

