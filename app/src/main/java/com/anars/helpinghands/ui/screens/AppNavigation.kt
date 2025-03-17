package com.anars.helpinghands.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anars.helpinghands.ui.home.HomeScreen

@Composable
fun  AppNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("intro_screen"){
            IntroScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("otp_screen") {
            OtpVerificationScreen(navController)
        }
        composable("home_screen") {
            HomeScreen(navController)
        }
    }

}