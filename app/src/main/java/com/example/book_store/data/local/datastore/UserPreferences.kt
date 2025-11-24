package com.example.book_store.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

private val Context.dataStore by preferencesDataStore(
    name = "user_preferences"
)

class  UserPreferences(private val  context: Context){
    private val dataStore=context.dataStore
    //Flow : onboardingDone
    val onboardingCompleteFlow =dataStore.data
        .catch { e  ->
            if(e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs -> prefs[UserKeys.ONBOARDING_COMPLETE]?:false}
    // Flow: loggedIn
    val loggedInFlow =dataStore.data
        .catch { e-> if(e is IOException ) emit(emptyPreferences()) else throw e
        }
        .map { prefs-> prefs[UserKeys.LOGGED_IN]?:false}
    // Mutations
    suspend fun setOnboardingDone(done: Boolean){
        dataStore.edit { prefs-> prefs[UserKeys.ONBOARDING_COMPLETE]=done }
    }
    suspend fun setLoggedIn( value: Boolean){
        dataStore.edit { prefs->prefs[UserKeys.LOGGED_IN]=value }
    }

}