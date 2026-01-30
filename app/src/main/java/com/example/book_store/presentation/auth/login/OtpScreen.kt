package com.example.book_store.presentation.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.book_store.presentation.navigation.Screen
import com.example.book_store.presentation.viewmodels.OtpViewModel

@Composable
fun OtpScreen(
    navController: NavHostController,
    email: String
) {
    val vm: OtpViewModel = hiltViewModel()

    LaunchedEffect(vm.success) {
        if (vm.success) {
            navController.navigate(Screen.BottomBar.route) {
                popUpTo(Screen.Otp.route) { inclusive = true }
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text("Enter OTP sent to $email")

            OutlinedTextField(
                value = vm.otp,
                onValueChange = { vm.otp = it },
                label = { Text("OTP") }
            )

            Button(
                onClick = { vm.verify(email) },
                enabled = !vm.loading
            ) {
                if (vm.loading) {
                    CircularProgressIndicator(Modifier.size(20.dp))
                } else {
                    Text("Verify")
                }
            }

            vm.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
