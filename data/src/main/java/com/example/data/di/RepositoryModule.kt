package com.example.data.di

import com.example.data.FilmsRepositoryImpl
import com.example.domain.FilmsRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::FilmsRepositoryImpl) {
        bind<FilmsRepository>()
    }
}