package com.example.booksearchapp.domain.usecase

import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetSavedBooksUseCase(
    private val repo: BookRepository
) {
    suspend operator fun invoke(): Flow<List<Book>>{
        return repo.getSavedBooks()
    }
}