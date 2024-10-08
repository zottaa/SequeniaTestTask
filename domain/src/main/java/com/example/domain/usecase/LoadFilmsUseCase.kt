package com.example.domain.usecase

import com.example.domain.interactor.FilmsInteractor

interface LoadFilmsUseCase {
    suspend operator fun invoke()
}

internal class LoadFilmsUseCaseImpl(
    private val interactor: FilmsInteractor
): LoadFilmsUseCase {
    override suspend fun invoke() {
        interactor.loadFilms()
    }
}