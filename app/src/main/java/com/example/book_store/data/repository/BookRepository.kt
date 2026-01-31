package com.example.book_store.data.repository

import com.example.book_store.data.model.BookPageResponse
import com.example.book_store.data.remote.ApiService
import retrofit2.Response
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val api: ApiService
) {

    suspend fun getBooks(page: Int, limit: Int): Result<BookPageResponse> {
        return try {
            val response: Response<BookPageResponse> =
                api.getBooks(page, limit)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to load books"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
