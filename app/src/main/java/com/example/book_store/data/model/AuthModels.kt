package com.example.book_store.data.model


data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)

data class VerifyRequest(

    val email: String,
    val otp: String
)

data class RegisterResponse(
    val message: String,
    val user: User?,
    val accessToken: String?,
    val refreshToken: String?
)

data class User(
    val id: String,
    val username: String,
    val email: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val user: UserDto
)

data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val profileImage: String
)
