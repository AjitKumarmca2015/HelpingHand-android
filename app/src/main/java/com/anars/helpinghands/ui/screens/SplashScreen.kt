package com.anars.helpinghands.ui.screens

import android.graphics.fonts.Font
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anars.helpinghands.R
import com.anars.helpinghands.ui.theme.HelpingHandsTheme
import com.anars.helpinghands.ui.theme.LatoFontFamily
import com.anars.helpinghands.ui.theme.backgroundColor
import com.anars.helpinghands.ui.theme.whiteColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    HelpingHandsTheme {
        LaunchedEffect(Unit) {
            delay(3000) // 3 seconds delay
            navController.navigate("intro_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        }


        // Splash screen UI
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            // Add your logo here
            Text(text = stringResource(id = R.string.splash_app_name),
                style = TextStyle(
                    color = whiteColor,
                    fontSize = 50.sp,
                    fontFamily = LatoFontFamily,
                    fontWeight = FontWeight.Light,
                    ),
                textAlign = TextAlign.Center

            )
            

        }
    }
}