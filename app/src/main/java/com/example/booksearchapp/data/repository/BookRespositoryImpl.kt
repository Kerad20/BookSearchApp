package com.example.booksearchapp.data.repository

import android.content.Context
import com.example.booksearchapp.core.util.pickBestFormat
import com.example.booksearchapp.data.local.dao.BookDao
import com.example.booksearchapp.data.local.entities.BookEntity
import com.example.booksearchapp.data.local.entities.toDomain
import com.example.booksearchapp.data.remote.api.ApiResult
import com.example.booksearchapp.data.remote.api.GutendexApi
import com.example.booksearchapp.data.remote.api.toMessage
import com.example.booksearchapp.data.remote.dto.BookDto
import com.example.booksearchapp.data.remote.dto.toDomain
import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.domain.repository.BookRepository
import com.example.booksearchapp.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File

class BookRespositoryImpl (
    private val api: GutendexApi,
    private val bookDao: BookDao
) : BookRepository {

    override suspend fun searchBooks(query: String): Resource<List<Book>> {

        val result = api.searchBooks(query)

        return when(result){
            is ApiResult.Success ->
            {
                Resource.Success(result.data.results.map {
                    it.toDomain()
                })
            }

            is ApiResult.Error -> {
                Resource.Error(result.error.toMessage())
            }
        }

    }

    override suspend fun getBook(id: Long): Resource<BookDto> {
        val result = api.getBook(id)

        return when(result){
            is ApiResult.Success ->
            {
                Resource.Success(result.data)
            }

            is ApiResult.Error -> {
                Resource.Error(result.error.toMessage())
            }
        }
    }

    override suspend fun saveOffline(book: Book, context: Context) {
        val format = pickBestFormat(book.formats)
            ?: throw IllegalStateException("No supported format")

        val file = File(context.filesDir, "${book.id}.${format.first}")

        api.downloadToFile(format.second, file)

        bookDao.insert(
            BookEntity(
                id = book.id,
                title = book.title,
                authors = book.authors,
                languages = book.languages,
                subjects = book.subjects,
                bookshelves = book.bookshelves,
                filePath = file.absolutePath,
                fileSize = file.length(),
                formats = book.formats,
                downloadCount = book.downloadCount,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override suspend fun getSavedBooks(): Flow<List<Book>> {
        return bookDao.getSavedBooks().map {
            books->
                books.map {
                    it.toDomain()
                }
        }
    }


}