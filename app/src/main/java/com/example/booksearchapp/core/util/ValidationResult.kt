package com.example.booksearchapp.core.util

sealed interface ValidationResult {

    data object Success : ValidationResult

    data class Error(
        val message: String
    ) : ValidationResult
}