package com.example.book_store.presentation.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.presentation.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

 @Composable
fun OnboardingScreen(navController: NavController,userPreferences: UserPreferences){
    Column(
modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center
    ){
Text("Welcome to book Store")
        Spacer(modifier = Modifier.height(32.dp))
        Text("Hope you will enjoy my first kotlin jetpack compose app")

        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                userPreferences.setOnboardingDone(true)
                kotlinx.coroutines.MainScope().launch {
                    navController.navigate(Screen.Register.route){
                        popUpTo(Screen.Onboarding.route){inclusive=true}
                    }
                }
            }
        }) { Text("Continue")}
    }
}