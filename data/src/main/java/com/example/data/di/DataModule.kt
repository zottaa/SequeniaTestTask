package com.example.data.di

import org.koin.dsl.module

fun dataModule() = module {
    includes(apiModule, databaseModule, dataMapperModule, repositoryModule, mergeStrategyModule)
}