package com.example.booksearchapp.data.remote.api

import com.example.booksearchapp.data.remote.dto.BookDto
import com.example.booksearchapp.data.remote.dto.BooksResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.prepareGet
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.jvm.javaio.copyTo
import java.io.File

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
    ): ApiResult<BookDto> {

        return safeCall {

            client.get(
                "https://gutendex.com/books/$id"
            )
        }
    }

    suspend fun downloadToFile(
        url: String,
        outputFile: File
    ): ApiResult<File> {

        return try {

            client.prepareGet(url).execute { response ->

                val channel = response.bodyAsChannel()

                outputFile.outputStream().use { output ->
                    channel.copyTo(output)
                }
            }

            ApiResult.Success(outputFile)

        } catch (e: Exception) {
            ApiResult.Error(e.toNetworkError())
        }
    }
}