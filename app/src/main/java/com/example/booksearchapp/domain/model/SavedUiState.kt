package com.example.booksearchapp.domain.model

data class SavedUiState(
    val books: List<Book> = emptyList(),
    val selectedBook: Book? = null
)
