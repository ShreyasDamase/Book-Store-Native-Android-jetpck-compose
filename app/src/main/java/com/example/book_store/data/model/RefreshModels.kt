package com.example.book_store.data.model

data class RefreshRequest(
    val refreshToken: String
)

data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)
