package com.example.domain.models

data class Film(
    val id: Long,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Float,
    val imageUrl: String,
    val description: String,
    val genres: List<String>
)