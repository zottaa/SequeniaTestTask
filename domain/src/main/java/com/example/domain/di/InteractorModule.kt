package com.example.domain.di

import com.example.domain.interactor.FilmsInteractor
import org.koin.dsl.module

internal val interactorModule = module {
    single {
        FilmsInteractor()
    }
}