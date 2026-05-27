package com.example.booksearchapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.domain.model.SearchUiState
import com.example.booksearchapp.domain.model.UiEvent
import com.example.booksearchapp.domain.repository.Resource
import com.example.booksearchapp.domain.usecase.SearchBooksUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchBooksUseCase: SearchBooksUseCase
): ViewModel() {

    val _state = MutableStateFlow(SearchUiState())

    val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    val state = _state.asStateFlow()

    fun searchBooks() {
        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            when(val books = searchBooksUseCase(_state.value.query)){
                is Resource.Success -> {
                    _state.update {
                        it.copy(books = books.data, isLoading = false)
                    }

                    _events.emit(UiEvent.NavigateToResults)
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    _events.emit(UiEvent.ShowError(books.message))
                }
            }
        }

    }

    fun onQueryChange(value: String) {
        _state.update {
            it.copy(query = value)
        }
    }

    fun selectBook(value: Book?){
        _state.update {
            it.copy(selectedBook = value)
        }
    }

}