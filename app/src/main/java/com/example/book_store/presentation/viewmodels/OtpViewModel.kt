package com.example.book_store.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    var otp by mutableStateOf("")
    var loading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var success by mutableStateOf(false)

    fun verify(email: String) {
        viewModelScope.launch {
            loading = true
            error = null

            val result = repo.verifyRegister(email, otp)

            loading = false

            result.onSuccess {
                success = true
            }.onFailure {
                error = it.message
            }
        }
    }
}
