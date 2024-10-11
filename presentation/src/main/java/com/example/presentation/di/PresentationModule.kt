package com.example.presentation.di

import org.koin.dsl.module

fun presentationModule() = module { includes(viewModelModule, mapperModule) }