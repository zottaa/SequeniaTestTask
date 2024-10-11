package com.example.domain.usecase.implementation

import com.example.domain.interactor.FilmsInteractor
import com.example.domain.usecase.LoadFilmByIdUseCase

internal class LoadFilmByIdUseCaseImpl(
    private val interactor: FilmsInteractor
) : LoadFilmByIdUseCase {
    override suspend fun invoke(id: Long) {
        interactor.loadFilm(id)
    }
}