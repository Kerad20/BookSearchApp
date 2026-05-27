package com.example.booksearchapp.di

import com.example.booksearchapp.data.remote.api.GutendexApi
import com.example.booksearchapp.data.remote.api.HttpClientFactory
import com.example.booksearchapp.data.repository.BookRespositoryImpl
import com.example.booksearchapp.domain.repository.BookRepository
import com.example.booksearchapp.domain.usecase.SearchBooksUseCase
import com.example.booksearchapp.presentation.search.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create() }
    single { GutendexApi(get()) }
    single<BookRepository> { BookRespositoryImpl(get ()) }
    single { SearchBooksUseCase(get()) }
    viewModel { SearchViewModel(get()) }

}