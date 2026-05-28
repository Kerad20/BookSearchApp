package com.example.booksearchapp.data.repository

import com.example.booksearchapp.data.local.dao.RecentSearchDao
import com.example.booksearchapp.data.local.entities.RecentSearchEntity
import com.example.booksearchapp.domain.repository.RecentSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecentSearchRepositoryImpl(
    private val recentSearchDao: RecentSearchDao
): RecentSearchRepository {
    override fun observeRecentSearches():
            Flow<List<String>> {

        return recentSearchDao
            .observeRecentSearches()
            .map { searches ->

                searches.map {
                    it.query
                }
            }
    }

    override suspend fun saveRecentSearch(
        query: String
    ) {

        recentSearchDao.insertSearch(
            RecentSearchEntity(
                query = query.trim(),
                timestamp = System.currentTimeMillis()
            )
        )

        recentSearchDao.trimToLatestSearches()
    }
}