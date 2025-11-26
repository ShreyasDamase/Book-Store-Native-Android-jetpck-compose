package com.example.book_store.data.repository

import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.data.model.*
import com.example.book_store.data.remote.RetrofitInstance
import org.json.JSONObject

class AuthRepository(private val tokenStore: TokenStore) {

    private val api = RetrofitInstance.apiService

    // ---------------- REGISTER ---------------- //
    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<RegisterResponse> {
        return try {
            val response = api.register(RegisterRequest(username, email, password))

            if (response.isSuccessful) {
                val body = response.body() ?: return Result.failure(Exception("Empty response"))

                // Save tokens
                body.accessToken?.let { access ->
                    body.refreshToken?.let { refresh ->
                        tokenStore.saveTokens(access, refresh)
                    }
                }

                Result.success(body)

            } else {
                Result.failure(Exception(parseError(response.errorBody()?.string())))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ---------------- LOGIN ---------------- //
    suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse> {
        return try {
            val response = api.login(LoginRequest(email, password))

            if (response.isSuccessful) {
                val body = response.body() ?: return Result.failure(Exception("Empty response"))

                // Save tokens
                tokenStore.saveTokens(body.accessToken, body.refreshToken)

                Result.success(body)

            } else {
                Result.failure(Exception(parseError(response.errorBody()?.string())))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // ---------------- ERROR PARSER ---------------- //
    private fun parseError(raw: String?): String {
        if (raw.isNullOrBlank()) return "Unknown error"

        return try {
            val json = JSONObject(raw)
            json.optString("message", "Something went wrong")
        } catch (e: Exception) {
            "Something went wrong"
        }
    }
}
