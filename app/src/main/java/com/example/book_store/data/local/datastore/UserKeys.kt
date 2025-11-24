package com.example.book_store.data.local.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
//create singleton class to store instance of keys using object
object UserKeys {
    val ONBOARDING_COMPLETE = booleanPreferencesKey("onboarding_complete")
   val LOGGED_IN=booleanPreferencesKey("logged_in")

}