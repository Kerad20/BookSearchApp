package com.example.booksearchapp.domain.model

sealed interface UiEvent {
    data object NavigateToResults : UiEvent
    data class ShowError(val message: String) : UiEvent
}