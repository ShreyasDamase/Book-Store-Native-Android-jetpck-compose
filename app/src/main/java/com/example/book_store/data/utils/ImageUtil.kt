package com.example.book_store.data.utils

import android.content.Context
import android.net.Uri
import android.util.Base64

fun uriToBase64(context: Context, uri: Uri): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val bytes = inputStream!!.readBytes()
    val base64 = Base64.encodeToString(bytes, Base64.DEFAULT)
    return "data:image/jpeg;base64,$base64"
}

