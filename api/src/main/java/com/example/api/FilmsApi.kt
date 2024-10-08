package com.example.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class FilmsApi internal constructor(
    baseUrl: String
) {
    private val instance: FilmsApiService

    init {
        instance = retrofit(baseUrl)
            .create()
    }

    private fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun fetch(): FilmsResult =
        try {
            val response = instance.fetch()
            FilmsResult.Success(response.films)
        } catch (exception: Exception) {
            FilmsResult.Error(exception)
        }
}

fun ProvideFilmsApi(
    baseUrl: String
) = FilmsApi(baseUrl)

