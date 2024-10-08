package com.example.domain.usecase

interface LoadFilmByIdUseCase {
    suspend operator fun invoke(id: Long)
}

