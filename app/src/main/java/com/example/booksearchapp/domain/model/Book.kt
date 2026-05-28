package com.example.booksearchapp.domain.model

import com.example.booksearchapp.data.remote.dto.Author
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Long,
    val title: String,
    val subjects: List<String>,
    val authors: List<Author>,
    val summaries: List<String>,
    val translators: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean?,
    val media_type: String,
    val formats: Map<String, String>,
    val download_count: Long
    )

