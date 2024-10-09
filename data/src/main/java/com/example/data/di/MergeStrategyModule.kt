package com.example.data.di

import com.example.data.FilmsLoadStatusMergeStrategy
import com.example.data.FilmsLoadStatusMergeStrategyImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val mergeStrategyModule = module {
    factoryOf(::FilmsLoadStatusMergeStrategyImpl) {
        bind<FilmsLoadStatusMergeStrategy>()
    }
}