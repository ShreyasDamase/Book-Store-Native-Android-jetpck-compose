package com.example.book_store.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    val tokenStore: TokenStore,
    val userPreferences: UserPreferences
) : ViewModel()
