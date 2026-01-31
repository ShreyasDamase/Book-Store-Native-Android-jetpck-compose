package com.example.book_store.data.remote


import com.example.book_store.data.model.BookPageResponse
import com.example.book_store.data.model.LoginRequest
import com.example.book_store.data.model.LoginResponse
import com.example.book_store.data.model.RegisterRequest
import com.example.book_store.data.model.RegisterResponse
import com.example.book_store.data.model.VerifyRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("auth/register-request")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/verify-register")
    suspend fun verifyRegister(
        @Body request: VerifyRequest
    ): Response<LoginResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>


    @GET("books")
    suspend fun getBooks(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<BookPageResponse>

   
}