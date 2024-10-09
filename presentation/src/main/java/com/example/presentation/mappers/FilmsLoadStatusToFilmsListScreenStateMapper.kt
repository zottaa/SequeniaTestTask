package com.example.presentation.mappers

import android.content.Context
import com.example.common.Mapper
import com.example.domain.error.DataError
import com.example.domain.models.FilmsLoadStatus
import com.example.presentation.R
import com.example.presentation.models.ErrorUi
import com.example.presentation.models.FilmUi
import com.example.presentation.screens.list.FilmsListScreenState

internal interface FilmsLoadStatusToFilmsListScreenStateMapper :
    Mapper<FilmsLoadStatus, FilmsListScreenState>

internal class FilmsLoadStatusToFilmsListScreenStateMapperImpl(
    private val context: Context,
    private val filmDomainToUiMapper: FilmDomainToUiMapper
) : FilmsLoadStatusToFilmsListScreenStateMapper {
    override fun map(from: FilmsLoadStatus): FilmsListScreenState {
        return when (from) {
            is FilmsLoadStatus.Error -> {
                val filmsUi = from.films.map { film ->
                    filmDomainToUiMapper.map(film)
                }
                val genres = getGenres(filmsUi)
                FilmsListScreenState.Error(
                    films = filmsUi,
                    genres = genres,
                    error = generateErrorUi(from)
                )
            }

            is FilmsLoadStatus.Loading -> {
                val filmsUi = from.films.map { film ->
                    filmDomainToUiMapper.map(film)
                }
                val genres = getGenres(filmsUi)
                FilmsListScreenState.Loading(
                    films = filmsUi,
                    genres = genres,
                )
            }

            is FilmsLoadStatus.Success -> {
                val filmsUi = from.films.map { film ->
                    filmDomainToUiMapper.map(film)
                }
                val genres = getGenres(filmsUi)
                FilmsListScreenState.Show(
                    films = filmsUi,
                    genres = genres
                )
            }
        }
    }

    private fun generateErrorUi(from: FilmsLoadStatus.Error): ErrorUi {
        val errorId = System.currentTimeMillis()
        val errorMessageResId = when (from.error) {
            DataError.NetworkError.NO_INTERNET -> R.string.no_internet_error
            else -> R.string.unknown_error
        }

        return ErrorUi(
            id = errorId,
            message = context.getString(errorMessageResId)
        )
    }

    private fun getGenres(filmsUi: List<FilmUi>) =
        filmsUi.flatMap { it.genres }.distinct()
}