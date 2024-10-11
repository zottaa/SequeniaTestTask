package com.example.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFilmUseCase
import com.example.domain.usecase.LoadFilmByIdUseCase
import com.example.presentation.mappers.FilmDomainToUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val FILM_ID = "filmId"

internal class FilmDetailsViewModel(
    private val filmDomainToUiMapper: FilmDomainToUiMapper,
    private val loadFilmByIdUseCase: LoadFilmByIdUseCase,
    private val getFilmUseCase: GetFilmUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableStateFlow<FilmDetailsScreenState>(FilmDetailsScreenState.Loading)

    private val state: StateFlow<FilmDetailsScreenState> = _state

    init {
        viewModelScope.launch {
            val id: Long? = savedStateHandle[FILM_ID]
            if (id != null) {
                observeFilm()
                loadFilmByIdUseCase(id)
            }
        }
    }

    fun getState(): StateFlow<FilmDetailsScreenState> = state

    private fun observeFilm() {
        getFilmUseCase().onEach { film ->
            _state.update {
                FilmDetailsScreenState.Show(filmDomainToUiMapper.map(film))
            }
        }.launchIn(viewModelScope)
    }
}

