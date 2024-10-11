package com.example.presentation.mappers

import com.example.common.Mapper
import com.example.domain.models.Film
import com.example.presentation.models.FilmUi

internal interface FilmDomainToUiMapper :
    Mapper<Film, FilmUi>

internal class FilmDomainToUiMapperImpl : FilmDomainToUiMapper {
    override fun map(from: Film): FilmUi {
        return FilmUi(
            id = from.id,
            localizedName = from.localizedName,
            name = from.name,
            year = from.year,
            rating = from.rating,
            imageUrl = from.imageUrl,
            description = from.description,
            genres = from.genres
        )
    }
}