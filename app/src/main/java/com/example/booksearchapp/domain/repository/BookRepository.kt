package com.example.booksearchapp.domain.repository

import android.content.Context
import com.example.booksearchapp.data.remote.dto.BookDto
import com.example.booksearchapp.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Resource<List<Book>>
    suspend fun getBook(id: Long): Resource<BookDto>
    suspend fun saveOffline(book: Book, context: Context)
    suspend fun getSavedBooks(): Flow<List<Book>>
}