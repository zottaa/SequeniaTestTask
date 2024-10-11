package com.example.data.di

import com.example.database.ProvideDatabase
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        ProvideDatabase(applicationContext = get())
    }
}