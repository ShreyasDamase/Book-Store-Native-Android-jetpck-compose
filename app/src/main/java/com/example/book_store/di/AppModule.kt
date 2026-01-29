package com.example.book_store.di


import android.content.Context
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
}
