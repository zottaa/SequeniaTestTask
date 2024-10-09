package com.example.domain.di

import com.example.domain.usecase.GetFilmUseCase
import com.example.domain.usecase.GetFilmsUseCase
import com.example.domain.usecase.LoadFilmByIdUseCase
import com.example.domain.usecase.LoadFilmsUseCase
import com.example.domain.usecase.implementation.GetFilmUseCaseImpl
import com.example.domain.usecase.implementation.GetFilmsUseCaseImpl
import com.example.domain.usecase.implementation.LoadFilmByIdUseCaseImpl
import com.example.domain.usecase.implementation.LoadFilmsUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val useCasesModule = module {
    factoryOf(::GetFilmsUseCaseImpl) {
        bind<GetFilmsUseCase>()
    }
    factoryOf(::GetFilmUseCaseImpl) {
        bind<GetFilmUseCase>()
    }
    factoryOf(::LoadFilmsUseCaseImpl) {
        bind<LoadFilmsUseCase>()
    }
    factoryOf(::LoadFilmByIdUseCaseImpl) {
        bind<LoadFilmByIdUseCase>()
    }
}