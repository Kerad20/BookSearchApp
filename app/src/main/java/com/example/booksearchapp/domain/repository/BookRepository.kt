package com.example.booksearchapp.domain.repository

import com.example.booksearchapp.domain.model.Book

interface BookRepository {
    suspend fun searchBooks(query: String): Resource<List<Book>>
    suspend fun getBook(id: Long): Resource<Book>
}