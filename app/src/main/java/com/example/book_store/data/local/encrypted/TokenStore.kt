package com.example.book_store.data.local.encrypted

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

class TokenStore(context: Context){
    companion object{
        private const val PREFS_NAME="secure_token"
        private const val KEY_ACCESS="access_token"
        private const val KEY_REFRESH="refresh_token"

    }

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme((MasterKey.KeyScheme.AES256_GCM))
        .build()

    private  val prefs = EncryptedSharedPreferences.create(
        context,PREFS_NAME,masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveTokens(accessToken: String,refreshToken: String){
        prefs.edit().apply{
            putString(KEY_ACCESS,accessToken)
            putString(KEY_REFRESH,refreshToken)
            apply()
        }
    }

    fun clearTokens(){
        prefs.edit { clear() }
    }
    fun getAccessToken(): String?= prefs.getString(KEY_ACCESS,null)
    fun getRefreshToken(): String?=prefs.getString(KEY_REFRESH,null)
}