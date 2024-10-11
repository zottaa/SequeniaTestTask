package com.example.presentation.screens.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetFilmsUseCase
import com.example.domain.usecase.LoadFilmsUseCase
import com.example.presentation.mappers.FilmsLoadStatusToFilmsListScreenStateMapper
import com.example.presentation.models.FilmUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val SELECTED_GENRE = "selectedGenre"

internal class FilmsListViewModel(
    private val loadFilmsUseCase: LoadFilmsUseCase,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val filmsLoadStatusToFilmsListScreenStateMapper: FilmsLoadStatusToFilmsListScreenStateMapper,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val selectedGenre: MutableStateFlow<String> = MutableStateFlow("")

    private val films: MutableStateFlow<List<FilmUi>> = MutableStateFlow(emptyList())

    private val _state: MutableStateFlow<FilmsListScreenState> =
        MutableStateFlow(FilmsListScreenState.Loading())

    private val state: StateFlow<FilmsListScreenState> = _state

    init {
        viewModelScope.launch {
            observeFilms()
            loadFilmsUseCase()
        }
    }

    fun refresh() {
        viewModelScope.launch {
            loadFilmsUseCase()
        }
    }

    fun saveGenre() {
        savedStateHandle[SELECTED_GENRE] = selectedGenre.value
    }

    fun restoreGenre() {
        val genre = savedStateHandle.get<String>(SELECTED_GENRE)
        if (genre != null && genre != selectedGenre.value) {
            selectGenre(genre)
        }
    }

    fun selectGenre(newGenre: String) {
        viewModelScope.launch {
            if (newGenre == selectedGenre.value) {
                selectedGenre.update {
                    ""
                }
            } else {
                selectedGenre.update {
                    newGenre
                }
            }
            _state.update { currentState ->
                applyGenreFilterOnState(currentState)
            }
        }
    }

    fun getState() = state

    private fun observeFilms() {
        getFilmsUseCase()
            .onEach { filmsLoadStatus ->
                println(filmsLoadStatus)
                val newState = filmsLoadStatusToFilmsListScreenStateMapper.map(filmsLoadStatus)
                handleNewState(newState)
            }.launchIn(viewModelScope)
    }

    private fun handleNewState(newState: FilmsListScreenState) {
        films.update {
            when (newState) {
                is FilmsListScreenState.Error -> newState.films
                is FilmsListScreenState.Loading -> newState.films
                is FilmsListScreenState.Show -> newState.films
            }
        }
        _state.update {
            applyGenreFilterOnState(newState)
        }
    }

    private fun applyGenreFilterOnState(currentState: FilmsListScreenState): FilmsListScreenState {
        val filteredFilms = filterFilmsByGenre(films.value, selectedGenre.value)
        return when (currentState) {
            is FilmsListScreenState.Error -> currentState.copy(
                films = filteredFilms,
                selectedGenre = selectedGenre.value
            )

            is FilmsListScreenState.Loading -> currentState.copy(
                films = filteredFilms,
                selectedGenre = selectedGenre.value
            )

            is FilmsListScreenState.Show -> currentState.copy(
                films = filteredFilms,
                selectedGenre = selectedGenre.value
            )
        }
    }

    private fun filterFilmsByGenre(films: List<FilmUi>, genre: String): List<FilmUi> {
        return films.filter { it.genres.contains(genre) || genre.isBlank() }
    }
}