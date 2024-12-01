package com.lasalle.hangman.Navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Game : Screen("game")
    object End : Screen("end")
    object Difficulty : Screen("difficulty")
} 