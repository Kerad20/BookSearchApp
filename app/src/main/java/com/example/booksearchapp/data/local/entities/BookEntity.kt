package com.example.booksearchapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.booksearchapp.data.remote.dto.Author
import com.example.booksearchapp.domain.model.Book

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val authors: List<Author>,
    val languages: List<String>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val filePath: String,
    val fileSize: Long,
    val formats: Map<String, String>,
    val downloadCount: Long,
    val timestamp: Long
)

fun BookEntity.toDomain(): Book {
    return Book(
        id = id,
        title = title,
        authors = authors,
        languages = languages,
        subjects = subjects,
        bookshelves = bookshelves,
        filePath = filePath,
        fileSize = fileSize,
        formats = formats,
        downloadCount = downloadCount,
        timestamp = timestamp
    )
}