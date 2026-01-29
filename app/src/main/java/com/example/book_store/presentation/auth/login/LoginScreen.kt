package com.example.book_store.presentation.auth.login
//DM Serif Display
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.data.local.encrypted.TokenStore
import com.example.book_store.data.repository.AuthRepository
import com.example.book_store.presentation.navigation.Screen
import com.example.book_store.presentation.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    userPreferences: UserPreferences,
    TokenStore: TokenStore.Companion
) {
    val context = LocalContext.current

    // Dependencies
    val tokenStore = remember { TokenStore(context) }
    val repo = remember { AuthRepository(tokenStore) }

    // ViewModel
    val viewModel = remember { LoginViewModel(repo, tokenStore) }

    // Navigate on success
    LaunchedEffect(viewModel.loginSuccess) {
        if (viewModel.loginSuccess) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.login() },
                enabled = !viewModel.loading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewModel.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text("Login")
                }
            }

            // Error message
            viewModel.errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // 👇 REGISTER NAVIGATION BUTTON
            Text(
                text = "Don't have an account? Register",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clickable {
                        navController.navigate(Screen.Register.route)
                    }
            )
        }
    }
}
