package com.example.book_store.data.remote

import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.data.model.RefreshRequest
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenStore: TokenStore,
    private val refreshApi: RefreshApiService
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        // prevent infinite loop
        if (response.request.header("Authorization") != null) {

            val refreshToken = tokenStore.getRefreshToken() ?: return null

            val refreshResponse = refreshApi
                .refreshToken(RefreshRequest(refreshToken))
                .execute()

            if (refreshResponse.isSuccessful) {
                val body = refreshResponse.body()!!

                // save new tokens
                tokenStore.saveTokens(
                    body.accessToken,
                    body.refreshToken
                )

                // retry original request with new token
                return response.request.newBuilder()
                    .header("Authorization", "Bearer ${body.accessToken}")
                    .build()
            }
        }

        return null
    }
}
