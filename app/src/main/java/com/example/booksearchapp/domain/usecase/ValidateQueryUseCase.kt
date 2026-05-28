package com.example.booksearchapp.domain.usecase

import com.example.booksearchapp.core.util.ValidationResult
import com.example.booksearchapp.core.util.Validators

class ValidateQueryUseCase {

    operator fun invoke(
        query: String
    ): ValidationResult {

        return Validators
            .validateSearchQuery(query)
    }
}