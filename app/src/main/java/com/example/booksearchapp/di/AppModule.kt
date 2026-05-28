package com.example.booksearchapp.di

import androidx.room.Room
import com.example.booksearchapp.data.local.database.AppDatabase
import com.example.booksearchapp.data.remote.api.GutendexApi
import com.example.booksearchapp.data.remote.api.HttpClientFactory
import com.example.booksearchapp.data.repository.BookRespositoryImpl
import com.example.booksearchapp.data.repository.RecentSearchRepositoryImpl
import com.example.booksearchapp.domain.repository.BookRepository
import com.example.booksearchapp.domain.repository.RecentSearchRepository
import com.example.booksearchapp.domain.usecase.GetRecentSearchUseCase
import com.example.booksearchapp.domain.usecase.GetSavedBooksUseCase
import com.example.booksearchapp.domain.usecase.SaveOfflineUseCase
import com.example.booksearchapp.domain.usecase.SaveRecentSearchUseCase
import com.example.booksearchapp.domain.usecase.SearchBooksUseCase
import com.example.booksearchapp.domain.usecase.ValidateQueryUseCase
import com.example.booksearchapp.presentation.saved.SavedViewModel
import com.example.booksearchapp.presentation.search.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { HttpClientFactory.create() }
    single { GutendexApi(get()) }
    single<AppDatabase> {

        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "book_search_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single {

        get<AppDatabase>()
            .recentSearchDao()
    }
    single {
        get<AppDatabase>()
            .bookDao()
    }
    single<BookRepository> { BookRespositoryImpl(get (), get ()) }
    single<RecentSearchRepository> { RecentSearchRepositoryImpl(get()) }

    single { GetRecentSearchUseCase(get()) }
    single { SaveRecentSearchUseCase(get()) }
    single { SearchBooksUseCase(get()) }
    single { ValidateQueryUseCase() }
    single { SaveOfflineUseCase(get()) }
    single { GetSavedBooksUseCase(get()) }

    viewModel { SearchViewModel(get(), get (),
        get(), get(), get ()) }

    viewModel { SavedViewModel(get()) }
}