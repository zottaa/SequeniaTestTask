package com.example.data.di

import com.example.data.mappers.FilmDBOtoDomainMapper
import com.example.data.mappers.FilmDBOtoDomainMapperImpl
import com.example.data.mappers.FilmDTOtoDBOMapper
import com.example.data.mappers.FilmDTOtoDBOMapperImpl
import com.example.data.mappers.FilmDTOtoDomainMapper
import com.example.data.mappers.FilmDTOtoDomainMapperImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val dataMapperModule = module {
    factoryOf(::FilmDTOtoDomainMapperImpl) {
        bind<FilmDTOtoDomainMapper>()
    }

    factoryOf(::FilmDBOtoDomainMapperImpl) {
        bind<FilmDBOtoDomainMapper>()
    }

    factoryOf(::FilmDTOtoDBOMapperImpl) {
        bind<FilmDTOtoDBOMapper>()
    }
}