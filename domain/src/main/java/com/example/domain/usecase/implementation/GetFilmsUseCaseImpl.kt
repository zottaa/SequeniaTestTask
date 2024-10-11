package com.example.domain.usecase.implementation

import com.example.domain.FilmsRepository
import com.example.domain.interactor.FilmsInteractor
import com.example.domain.models.FilmsLoadStatus
import com.example.domain.usecase.GetFilmsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

internal class GetFilmsUseCaseImpl(
    interactor: FilmsInteractor,
    private val repository: FilmsRepository
) : GetFilmsUseCase {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val films = interactor.observeFilms().flatMapLatest {
        repository.films().map { loadStatus: FilmsLoadStatus ->
            sortFilms(loadStatus)
        }
    }

    override fun invoke(): Flow<FilmsLoadStatus> =
        films

    private fun sortFilms(loadStatus: FilmsLoadStatus) =
        when (loadStatus) {
            is FilmsLoadStatus.Error -> loadStatus.copy(films = loadStatus.films.sortedBy { it.localizedName })
            is FilmsLoadStatus.Loading -> loadStatus.copy(films = loadStatus.films.sortedBy { it.localizedName })
            is FilmsLoadStatus.Success -> loadStatus.copy(films = loadStatus.films.sortedBy { it.localizedName })
        }
}