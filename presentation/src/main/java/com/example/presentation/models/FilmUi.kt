package com.example.presentation.models

data class FilmUi(
    val id: Long,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Float,
    val imageUrl: String,
    val description: String,
    val genres: List<String>
)