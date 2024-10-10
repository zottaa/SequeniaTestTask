package com.example.data.mappers

import com.example.api.models.FilmDTO
import com.example.common.Mapper
import com.example.database.models.FilmDBO

internal interface FilmDTOtoDBOMapper : Mapper<FilmDTO, FilmDBO> {
    override fun map(from: FilmDTO): FilmDBO
}

internal class FilmDTOtoDBOMapperImpl : FilmDTOtoDBOMapper {
    override fun map(from: FilmDTO): FilmDBO {
        return FilmDBO(
            id = from.id,
            localizedName = from.localizedName ?: "",
            name = from.name ?: "",
            year = from.year ?: 1970,
            rating = from.rating ?: 0f,
            imageUrl = from.imageUrl ?: "",
            genres = from.genres.map { it.replaceFirstChar { char -> char.uppercaseChar() } },
            description = from.description ?: "",
        )
    }
}