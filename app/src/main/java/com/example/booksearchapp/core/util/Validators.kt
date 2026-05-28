package com.example.booksearchapp.core.util

object Validators {

    fun validateSearchQuery(
        query: String
    ): ValidationResult {

        val trimmed = query.trim()

        return when {

            trimmed.isBlank() -> {
                ValidationResult.Error(
                    "Query cannot be empty"
                )
            }

            trimmed.length < 3 -> {
                ValidationResult.Error(
                    "Minimum 3 characters required"
                )
            }

            else -> {
                ValidationResult.Success
            }
        }
    }
}