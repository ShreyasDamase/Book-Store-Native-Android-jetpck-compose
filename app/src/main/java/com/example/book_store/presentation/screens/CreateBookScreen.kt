package com.example.book_store.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.book_store.data.utils.uriToBase64
import com.example.book_store.presentation.viewmodels.CreateBookViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun CreateBookScreen(
    vm: CreateBookViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val book = vm.book

    var selectedUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedUri = it
            val base64 = uriToBase64(context, it)
            vm.onImageChange(base64)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Button(onClick = { launcher.launch("image/*") }) {
            Text("Pick Image")
        }

        // ✅ Image preview
        selectedUri?.let {
            AsyncImage(
                model = it,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        OutlinedTextField(
            value = book.title,
            onValueChange = vm::onTitleChange,
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = book.caption,
            onValueChange = vm::onCaptionChange,
            label = { Text("Caption") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = book.rating.toString(),
            onValueChange = {
                it.toIntOrNull()?.let(vm::onRatingChange)
            },
            label = { Text("Rating (1-5)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = book.categories.joinToString(","),
            onValueChange = {
                vm.onCategoriesChange(
                    it.split(",").map { s -> s.trim() }
                )
            },
            label = { Text("Categories (comma separated)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { vm.createBook() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Book")
        }
    }
}
