package com.example.booksearchapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface RecentSearchRepository {
    fun observeRecentSearches():
            Flow<List<String>>

    suspend fun saveRecentSearch(
        query: String
    )
}