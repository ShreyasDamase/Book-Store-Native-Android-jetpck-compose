package com.example.book_store.data.model

data class BookPageResponse(
    val books: List<BookDto>,
    val currentPage: Int,
    val totalBooks: Int,
    val totalPages: Int
)

data class CreateBookRequest(
    val title: String,
    val caption: String,
    val rating: Int,
    val image: String,
    val categories: List<String>
)

data class CreateBookResponse(
    val _id: String,
    val title: String,
    val caption: String,
    val rating: Int,
    val image: String,
    val categories: List<String>,
    val user: String
)


data class BookDto(
    val _id: String,
    val title: String,
    val caption: String,
    val image: String,
    val categories: List<String>,
    val rating: Int,
    val user: UserMini
)

data class UserMini(
    val username: String,
    val profileImage: String
)