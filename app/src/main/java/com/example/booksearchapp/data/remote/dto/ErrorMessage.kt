package com.example.booksearchapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorMessage(
    val detail: String
)