package com.example.domain.di

import org.koin.dsl.module

fun domainModule() = module {
    includes(interactorModule, useCasesModule)
}