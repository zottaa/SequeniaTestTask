package com.example.presentation.di

import com.example.presentation.screens.details.FilmDetailsViewModel
import com.example.presentation.screens.list.FilmsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModelOf(::FilmsListViewModel)
    viewModelOf(::FilmDetailsViewModel)
}