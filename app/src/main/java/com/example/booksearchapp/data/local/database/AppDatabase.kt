package com.example.booksearchapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.booksearchapp.data.local.dao.BookDao
import com.example.booksearchapp.data.local.dao.RecentSearchDao
import com.example.booksearchapp.data.local.entities.BookEntity
import com.example.booksearchapp.data.local.entities.RecentSearchEntity

@Database(
    entities = [
        BookEntity::class,
        RecentSearchEntity::class
    ],
    version = 5
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    abstract fun recentSearchDao(): RecentSearchDao
}