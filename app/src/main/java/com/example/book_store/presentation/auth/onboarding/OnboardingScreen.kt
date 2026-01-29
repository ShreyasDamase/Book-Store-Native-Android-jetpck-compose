package com.example.book_store.presentation.auth.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ImageLoader
import com.example.book_store.data.local.datastore.UserPreferences
import com.example.book_store.presentation.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.book_store.R
import com.example.book_store.presentation.viewmodels.SessionViewModel

@Composable
fun OnboardingScreen(
    navController: NavController,
) {
    val context = LocalContext.current
    val sessionVM: SessionViewModel = hiltViewModel()

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Main content (centered)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Welcome to Book Store", fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                "Hope you will enjoy my first Kotlin Jetpack Compose app",
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(50.dp))

            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(R.raw.onboarding_main)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = null,
                modifier = Modifier.size(350.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                "Start reading", fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(" Whatever you want?", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)

        }
// Bottom button (absolute)
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    sessionVM.userPreferences.setOnboardingDone(true)
                    MainScope().launch {
                        navController.navigate(Screen.Register.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-80).dp)  // bottom margin

                .size(70.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFebb784),
                contentColor = Color.Yellow
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 5.dp),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(R.raw.arrow)
                        .build(),
                    imageLoader = imageLoader,
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    colorFilter = ColorFilter.tint(Color.White),


                    )
            }

        }
    }
}
