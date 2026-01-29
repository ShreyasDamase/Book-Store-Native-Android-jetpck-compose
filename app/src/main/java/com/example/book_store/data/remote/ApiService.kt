package com.example.book_store.data.remote


 import com.example.book_store.data.model.LoginRequest
 import com.example.book_store.data.model.LoginResponse
 import com.example.book_store.data.model.RegisterRequest
 import com.example.book_store.data.model.RegisterResponse
 import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/register-request")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}