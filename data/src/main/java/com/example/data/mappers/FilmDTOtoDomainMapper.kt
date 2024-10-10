package com.example.data.mappers

import com.example.api.models.FilmDTO
import com.example.common.Mapper
import com.example.domain.models.Film

internal interface FilmDTOtoDomainMapper : Mapper<FilmDTO, Film> {
    override fun map(from: FilmDTO): Film
}

internal class FilmDTOtoDomainMapperImpl : FilmDTOtoDomainMapper {
    override fun map(from: FilmDTO): Film {
        return Film(
            id = from.id,
            localizedName = from.localizedName ?: "",
            name = from.name ?: "",
            year = from.year ?: 1970,
            rating = from.rating ?: 0f,
            imageUrl = from.imageUrl ?: "",
            genres = from.genres,
            description = from.description ?: "",
        )
    }
}