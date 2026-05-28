package com.example.booksearchapp.presentation.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchapp.domain.model.SavedUiState
import com.example.booksearchapp.domain.usecase.GetSavedBooksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SavedViewModel(
    private val getSavedBooksUseCase: GetSavedBooksUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SavedUiState())
    val state = _state.asStateFlow()

    init {
        getSavedBooks()
    }

    fun getSavedBooks(){
        viewModelScope.launch {
            getSavedBooksUseCase()
                .collectLatest { books ->
                        _state.update {
                            it.copy(books = books)
                        }
                }
        }
    }

}