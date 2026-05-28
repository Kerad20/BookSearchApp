package com.example.booksearchapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookDto>
)