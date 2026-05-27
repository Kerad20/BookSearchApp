package com.example.booksearchapp.domain.repository

import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.data.remote.dto.BooksResponse

interface BookRepository {
    suspend fun searchBooks(query: String): Resource<List<Book>>
    suspend fun getBook(id: Long): Resource<Book>
}