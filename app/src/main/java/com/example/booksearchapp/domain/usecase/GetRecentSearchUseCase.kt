package com.example.booksearchapp.domain.usecase

import com.example.booksearchapp.domain.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow

class GetRecentSearchUseCase(
    private val repo: RecentSearchRepository
) {
    operator fun invoke():
            Flow<List<String>> {

        return repo
            .observeRecentSearches()
    }
}