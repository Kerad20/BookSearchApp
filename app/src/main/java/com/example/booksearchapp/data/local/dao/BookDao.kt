package com.example.booksearchapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksearchapp.data.local.entities.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books ORDER BY timestamp DESC")
    fun getSavedBooks(): Flow<List<BookEntity>>
//
//    @Query("SELECT * FROM books WHERE id = :id")
//    suspend fun getBook(id: Int): BookEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

//    @Query("UPDATE books SET isDownloaded = :downloaded, filePath = :path, fileSize = :size WHERE id = :id")
//    suspend fun updateDownloadState(
//        id: Int,
//        downloaded: Boolean,
//        path: String,
//        size: Long
//    )
}