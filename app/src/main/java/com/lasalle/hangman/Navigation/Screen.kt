package com.lasalle.hangman.Navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Game : Screen("game/{difficulty}") {
        fun createRoute(difficulty: String) = "game/$difficulty"
    }
    object End : Screen("end/{hasWon}")
    object Difficulty : Screen("difficulty")
} 