package com.example.api.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDTO(
    @SerialName(value = "id")
    val id: Long,
    @SerialName(value = "localized_name")
    val localizedName: String?,
    @SerialName(value = "name")
    val name: String?,
    @SerialName(value = "year")
    val year: Int?,
    @SerialName(value = "rating")
    val rating: Float?,
    @SerialName(value = "image_url")
    val imageUrl: String?,
    @SerialName(value = "description")
    val description: String?,
    @SerialName(value = "genres")
    val genres: List<String>
)