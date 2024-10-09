package com.example.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


class FilmsApi internal constructor(
    baseUrl: String
) {
    private val instance: FilmsApiService

    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    init {
        instance = retrofit(baseUrl, gson)
            .create()
    }



    private fun retrofit(baseUrl: String, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()



    suspend fun fetch(): FilmsResult =
        try {
            val response = instance.fetch()
            println(response.films)
            FilmsResult.Success(response.films)
        } catch (exception: Exception) {
            FilmsResult.Error(exception)
        }
}

fun ProvideFilmsApi(
    baseUrl: String
) = FilmsApi(baseUrl)

