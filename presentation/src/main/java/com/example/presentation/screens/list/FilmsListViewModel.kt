package com.example.presentation.screens.list

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

internal class FilmsListViewModel(
    private val loadFilmsUseCase: LoadFilmsUseCase,
    private val getFilmsUseCase: GetFilmsUseCase,
    private val filmsLoadStatusToFilmsListScreenStateMapper: FilmsLoadStatusToFilmsListScreenStateMapper
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
                when (currentState) {
                    is FilmsListScreenState.Error -> currentState.copy(films = films.value.filter { film ->
                        film.genres.contains(
                            selectedGenre.value
                        ) or
                                selectedGenre.value.isBlank()
                    }, selectedGenre = selectedGenre.value)

                    is FilmsListScreenState.Loading -> currentState.copy(films = films.value.filter { film ->
                        film.genres.contains(
                            selectedGenre.value
                        ) or selectedGenre.value.isBlank()
                    }, selectedGenre = selectedGenre.value)

                    is FilmsListScreenState.Show -> currentState.copy(films = films.value.filter { film ->
                        film.genres.contains(
                            selectedGenre.value
                        ) or selectedGenre.value.isBlank()
                    }, selectedGenre = selectedGenre.value)
                }
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
            newState
        }
    }
}