package com.example.book_store.di


import android.content.Context
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.data.remote.ApiService
import com.example.book_store.data.remote.AuthInterceptor
import com.example.book_store.data.remote.RefreshApiService
import com.example.book_store.data.remote.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenStore(
        @ApplicationContext context: Context
    ): TokenStore {
        return TokenStore(context)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(
        @ApplicationContext context: Context
    ): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    @RefreshRetrofit
    fun provideRefreshRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideRefreshApi(
        @RefreshRetrofit retrofit: Retrofit
    ): RefreshApiService {
        return retrofit.create(RefreshApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttp(
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator

    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)

            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/api/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
