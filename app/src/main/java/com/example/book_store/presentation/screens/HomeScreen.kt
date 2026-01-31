package com.example.book_store.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.book_store.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel: HomeViewModel = hiltViewModel()

    val books = viewModel.books
    val loading = viewModel.loading

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        itemsIndexed(books) { index, book ->

            // 🔥 Trigger pagination when reaching end
            if (index >= books.size - 1 && !loading) {
                viewModel.loadBooks()
            }

            BookItem(
                title = book.title,
                caption = book.caption,
                image = book.image,
                username = book.user.username
            )
        }

        if (loading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun BookItem(
    title: String,
    caption: String,
    image: String,
    username: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Image(
                painter = rememberAsyncImagePainter(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(8.dp))

            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(caption, style = MaterialTheme.typography.bodySmall)
            Text("By $username", style = MaterialTheme.typography.labelSmall)
        }
    }
}
