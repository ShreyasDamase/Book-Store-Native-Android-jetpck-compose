package com.example.book_store.data.remote

import com.example.book_store.data.model.RefreshRequest
import com.example.book_store.data.model.RefreshResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshApiService {

    @POST("auth/refresh")
    fun refreshToken(
        @Body request: RefreshRequest
    ): Call<RefreshResponse>
}
