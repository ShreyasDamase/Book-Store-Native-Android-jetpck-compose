package com.example.book_store.presentation.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.data.model.LoginRequest
import com.example.book_store.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository,
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
                loginSuccess = true
            }

            result.onFailure { e ->
                errorMessage = e.message ?: "Login failed"
            }

            loading = false
        }
    }
}