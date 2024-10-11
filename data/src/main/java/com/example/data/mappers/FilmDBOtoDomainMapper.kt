package com.example.data.mappers

import com.example.common.Mapper
import com.example.database.models.FilmDBO
import com.example.domain.models.Film

internal interface FilmDBOtoDomainMapper : Mapper<FilmDBO, Film> {
    override fun map(from: FilmDBO): Film
}

internal class FilmDBOtoDomainMapperImpl : FilmDBOtoDomainMapper {
    override fun map(from: FilmDBO): Film {
        return Film(
            id = from.id,
            localizedName = from.localizedName,
            name = from.name,
            year = from.year,
            rating = from.rating,
            imageUrl = from.imageUrl,
            genres = from.genres,
            description = from.description,
        )
    }
}