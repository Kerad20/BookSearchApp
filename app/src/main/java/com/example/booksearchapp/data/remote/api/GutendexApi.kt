package com.example.booksearchapp.data.remote.api

import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.data.remote.dto.BooksResponse
import com.example.booksearchapp.data.remote.dto.ErrorMessage
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GutendexApi(
    private val client: HttpClient
) {

    suspend fun searchBooks(
        query: String
    ): ApiResult<BooksResponse> {

        return safeCall {
            client.get(
                "https://gutendex.com/books"
            ) {

                parameter("search", query)
            }
        }
    }

    suspend fun getBook(
        id: Long
    ): ApiResult<Book> {

        return safeCall {

            client.get(
                "https://gutendex.com/books/$id"
            )
        }
    }
}