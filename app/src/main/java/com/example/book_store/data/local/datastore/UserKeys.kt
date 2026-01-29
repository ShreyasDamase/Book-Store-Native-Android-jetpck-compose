package com.example.book_store.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

//create singleton class to store instance of keys using object
object UserKeys {
    val ONBOARDING_COMPLETE = booleanPreferencesKey("onboarding_complete")
   val LOGGED_IN=booleanPreferencesKey("logged_in")
    val USER_NAME = stringPreferencesKey("user_name")
}