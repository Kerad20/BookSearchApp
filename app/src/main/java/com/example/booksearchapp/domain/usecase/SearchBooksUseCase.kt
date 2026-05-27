package com.example.booksearchapp.domain.usecase

import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.domain.repository.BookRepository
import com.example.booksearchapp.domain.repository.Resource

class SearchBooksUseCase(
    private val repo: BookRepository
) {
    suspend operator fun invoke(query: String): Resource<List<Book>> {
        return repo.searchBooks(
            query
        )
    }

}