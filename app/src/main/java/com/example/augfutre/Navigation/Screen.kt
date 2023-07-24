package com.example.augfutre.Navigation

sealed class Screen( val route: String) {

    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("Login_Screen")

}
