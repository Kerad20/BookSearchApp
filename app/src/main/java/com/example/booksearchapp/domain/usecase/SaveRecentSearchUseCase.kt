package com.example.booksearchapp.domain.usecase

import com.example.booksearchapp.domain.repository.RecentSearchRepository

class SaveRecentSearchUseCase(
    private val repo: RecentSearchRepository
) {

    suspend operator fun invoke(
        query: String
    ) {

        repo.saveRecentSearch(query)
    }
}