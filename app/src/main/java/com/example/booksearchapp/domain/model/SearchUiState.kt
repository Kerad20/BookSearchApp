package com.example.booksearchapp.domain.model

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val recentSearches: List<String> = emptyList(),
    val selectedBook: Book? = null
)
