package com.example.booksearchapp.domain.usecase

import android.content.Context
import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.domain.repository.BookRepository

class SaveOfflineUseCase(
    private val repo: BookRepository
) {
    suspend operator fun invoke(book: Book, context: Context) {
        repo.saveOffline(book, context)
    }
}