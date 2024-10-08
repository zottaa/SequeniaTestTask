package com.example.domain.usecase.implementation

import com.example.domain.FilmsRepository
import com.example.domain.interactor.FilmsInteractor
import com.example.domain.models.FilmsLoadStatus
import com.example.domain.usecase.GetFilmsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

internal class GetFilmsUseCaseImpl(
    interactor: FilmsInteractor,
    private val repository: FilmsRepository
) : GetFilmsUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val films = interactor.observeFilms().flatMapConcat {
        repository.films()
    }

    override fun invoke(): Flow<FilmsLoadStatus> {
        return films
    }
}