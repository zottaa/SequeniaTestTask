package com.example.domain.usecase.implementation

import com.example.domain.interactor.FilmsInteractor
import com.example.domain.usecase.LoadFilmsUseCase

internal class LoadFilmsUseCaseImpl(
    private val interactor: FilmsInteractor
): LoadFilmsUseCase {
    override suspend fun invoke() {
        interactor.loadFilms()
    }
}