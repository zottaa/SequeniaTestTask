package com.example.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDTO(
    @SerialName("id")
    val id: Long,
    @SerialName("localized_name")
    val localizedName: String,
    @SerialName("name")
    val name: String,
    @SerialName("year")
    val year: Int,
    @SerialName("rating")
    val rating: Float,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("description")
    val description: String,
    @SerialName("genres")
    val genres: List<String>
)