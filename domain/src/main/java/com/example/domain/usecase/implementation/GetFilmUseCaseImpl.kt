package com.example.domain.usecase.implementation

import com.example.domain.FilmsRepository
import com.example.domain.interactor.FilmsInteractor
import com.example.domain.models.Film
import com.example.domain.usecase.GetFilmUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

internal class GetFilmUseCaseImpl(
    interactor: FilmsInteractor,
    private val repository: FilmsRepository
) : GetFilmUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val film = interactor.observeFilm().flatMapLatest { id ->
        repository.film(id)
    }

    override fun invoke(): Flow<Film> =
        film
}