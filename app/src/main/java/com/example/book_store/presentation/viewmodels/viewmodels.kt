package com.example.book_store.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.data.model.LoginRequest
import com.example.book_store.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository,
    private val tokenStore: TokenStore
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var loading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null) 
    var loginSuccess by mutableStateOf(false)

    fun login() {
        viewModelScope.launch {
            loading = true
            errorMessage = null

            val result = repository.login(email, password)

            result.onSuccess { response ->
                tokenStore.saveTokens(response.accessToken, response.refreshToken)
                loginSuccess = true
            }

            result.onFailure { e ->
                errorMessage = e.message ?: "Login failed"
            }

            loading = false
        }
    }
}