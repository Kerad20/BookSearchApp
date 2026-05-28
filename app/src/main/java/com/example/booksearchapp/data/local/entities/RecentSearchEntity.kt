package com.example.booksearchapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_searches")
data class RecentSearchEntity(

    @PrimaryKey
    @ColumnInfo(name = "search_query")
    val query: String,

    val timestamp: Long
)
