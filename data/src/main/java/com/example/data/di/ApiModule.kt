package com.example.data.di

import com.example.api.ProvideFilmsApi
import org.koin.dsl.module

internal val apiModule = module {
    single {
        ProvideFilmsApi(baseUrl = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
    }
}