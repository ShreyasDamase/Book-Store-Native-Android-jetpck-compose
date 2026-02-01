package com.example.book_store.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import com.example.book_store.data.model.CreateBookRequest
import com.example.book_store.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@HiltViewModel

class CreateBookViewModel @Inject constructor(
    private val repo: BookRepository
) : ViewModel() {
    var book by mutableStateOf(
        CreateBookRequest(
            title = "",
            caption = "",
            rating = 0,
            image = "",
            categories = emptyList()
        )
    )
    var loading by mutableStateOf(false)

    fun onTitleChange(value: String) {
        book = book.copy(title = value)
    }

    fun onCaptionChange(value: String) {
        book = book.copy(caption = value)
    }

    fun onRatingChange(value: Int) {
        book = book.copy(rating = value)

    }

    fun onImageChange(value: String) {
        book = book.copy(image = value)
    }

    fun onCategoriesChange(value: List<String>) {
        book = book.copy(categories = value)
    }


    fun createBook() {
        if (loading) return

        viewModelScope.launch {
            loading = true

            repo.createBook(book).onSuccess {
                book = CreateBookRequest(
                    title = "",
                    caption = "",
                    rating = 0,
                    image = "",
                    categories = emptyList()
                )


            }

            loading = false
        }
    }
}
