package com.example.presentation.di

import com.example.presentation.mappers.FilmDomainToUiMapper
import com.example.presentation.mappers.FilmDomainToUiMapperImpl
import com.example.presentation.mappers.FilmsLoadStatusToFilmsListScreenStateMapper
import com.example.presentation.mappers.FilmsLoadStatusToFilmsListScreenStateMapperImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val mapperModule = module {
    factoryOf(::FilmDomainToUiMapperImpl) {
        bind<FilmDomainToUiMapper>()
    }

    factoryOf(::FilmsLoadStatusToFilmsListScreenStateMapperImpl) {
        bind<FilmsLoadStatusToFilmsListScreenStateMapper>()
    }
}