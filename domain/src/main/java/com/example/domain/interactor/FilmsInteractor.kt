package com.example.domain.interactor

import kotlinx.coroutines.flow.MutableSharedFlow

internal class FilmsInteractor {
    private val streamFilms: MutableSharedFlow<Unit> =
        MutableSharedFlow(replay = 1)

    private val streamFilm: MutableSharedFlow<Long> =
        MutableSharedFlow(replay = 1)

    fun loadFilms() {
        streamFilms.tryEmit(Unit)
    }

    fun loadFilm(id: Long) {
        streamFilm.tryEmit(id)
    }

    fun observeFilms() = streamFilms

    fun observeFilm() = streamFilm
}