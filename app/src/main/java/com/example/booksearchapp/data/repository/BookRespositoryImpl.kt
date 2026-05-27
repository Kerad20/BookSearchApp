package com.example.booksearchapp.data.repository

import com.example.booksearchapp.data.remote.api.ApiResult
import com.example.booksearchapp.data.remote.api.GutendexApi
import com.example.booksearchapp.data.remote.api.onError
import com.example.booksearchapp.data.remote.api.onSuccess
import com.example.booksearchapp.data.remote.api.toMessage
import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.data.remote.dto.BooksResponse
import com.example.booksearchapp.domain.repository.BookRepository
import com.example.booksearchapp.domain.repository.Resource

class BookRespositoryImpl (
    private val api: GutendexApi
) : BookRepository {

    override suspend fun searchBooks(query: String): Resource<List<Book>> {

        val result = api.searchBooks(query)

        return when(result){
            is ApiResult.Success ->
            {
                Resource.Success(result.data.results)
            }

            is ApiResult.Error -> {
                Resource.Error(result.error.toMessage())
            }
        }

//        api.searchBooks(query).onSuccess {
//            return Resource.Success(it.results)
//        }
//            .onError {
//                println(it.detail)
//                return Resource.Error(it.detail)
//            }
//        return Resource.Error("Unknown")
    }

    override suspend fun getBook(id: Long): Resource<Book> {
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

}