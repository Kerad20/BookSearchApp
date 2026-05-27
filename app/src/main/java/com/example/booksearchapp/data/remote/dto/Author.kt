package com.example.booksearchapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val birth_year: Int?,
    val death_year: Int?,
    val name: String
)