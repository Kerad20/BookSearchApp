package com.example.booksearchapp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchapp.core.util.ValidationResult
import com.example.booksearchapp.domain.model.Book
import com.example.booksearchapp.domain.model.SearchUiState
import com.example.booksearchapp.domain.model.UiEvent
import com.example.booksearchapp.domain.repository.Resource
import com.example.booksearchapp.domain.usecase.GetRecentSearchUseCase
import com.example.booksearchapp.domain.usecase.SaveRecentSearchUseCase
import com.example.booksearchapp.domain.usecase.SearchBooksUseCase
import com.example.booksearchapp.domain.usecase.ValidateQueryUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchBooksUseCase: SearchBooksUseCase,
    private val validateQueryUseCase: ValidateQueryUseCase,
    private val getRecentSearchUseCase: GetRecentSearchUseCase,
    private val saveRecentSearchUseCase: SaveRecentSearchUseCase
): ViewModel() {

    init {
        observeRecentSearches()
    }

    val _state = MutableStateFlow(SearchUiState())

    val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    val state = _state.asStateFlow()

    fun searchBooks(query: String) {

        when(val validation = validateQueryUseCase(query)){
            is ValidationResult.Success -> {
                performSearch(query)
            }
            is ValidationResult.Error -> {
                viewModelScope.launch {
                    _events.emit(UiEvent.ShowError(validation.message))
                }

            }
        }

    }

    private fun performSearch(query: String){
        _state.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            when(val books = searchBooksUseCase(query.trim())){
                is Resource.Success -> {
                    _state.update {
                        it.copy(books = books.data, isLoading = false)
                    }

                    saveRecentSearchUseCase(query)

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

    private fun observeRecentSearches() {

        viewModelScope.launch {

            getRecentSearchUseCase()
                .collectLatest { searches ->

                    _state.update {
                        it.copy(
                            recentSearches = searches
                        )
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