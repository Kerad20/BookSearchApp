package com.example.booksearchapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksearchapp.data.local.dao.RecentSearchDao
import com.example.booksearchapp.data.local.entities.RecentSearchEntity

@Database(
    entities = [
    //    SavedBookEntity::class,
        RecentSearchEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

   // abstract fun savedBooksDao(): SavedBooksDao

    abstract fun recentSearchDao(): RecentSearchDao
}