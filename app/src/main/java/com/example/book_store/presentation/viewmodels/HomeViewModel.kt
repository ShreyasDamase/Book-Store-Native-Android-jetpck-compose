package com.example.book_store.presentation.viewmodels

import com.example.book_store.data.repository.BookRepository
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.model.BookDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {

    var books by mutableStateOf<List<BookDto>>(emptyList())
    var loading by mutableStateOf(false)
    var page by mutableStateOf(1)
    var endReached by mutableStateOf(false)

    init {
        loadBooks()
    }

    fun loadBooks() {
        if (loading || endReached) return
        
        viewModelScope.launch {
            loading = true

            repo.getBooks(page, 10).onSuccess { response ->
                books = books + response.books
                page++

                if (page > response.totalPages) {
                    endReached = true
                }
            }

            loading = false
        }
    }
}