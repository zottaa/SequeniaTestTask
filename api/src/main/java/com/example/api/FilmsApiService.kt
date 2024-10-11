package com.example.api

import com.example.api.models.FilmsResponse
import retrofit2.http.GET

internal interface FilmsApiService {
    @GET("films.json")
    suspend fun fetch(): FilmsResponse
}