package com.example.booksearchapp.domain.model

import androidx.room.PrimaryKey
import com.example.booksearchapp.data.remote.dto.Author

data class Book(
    val id: Long,
    val title: String,
    val authors: List<Author>,
    val languages: List<String>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val filePath: String?,
    val fileSize: Long?,
    val formats: Map<String, String>,
    val downloadCount: Long,
    val timestamp: Long?
)
