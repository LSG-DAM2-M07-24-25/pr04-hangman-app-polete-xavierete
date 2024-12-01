/**
 * Screen.kt
 * 
 * This sealed class defines the different screens/routes used in the Hangman app's navigation graph.
 * Each object represents a screen with its corresponding route. Some screens require parameters
 * which are handled through their respective createRoute functions.
 */

package com.lasalle.hangman.Navigation

sealed class Screen(val route: String) {
    // Splash Screen Route
    object Splash : Screen("splash")

    // Home Screen Route
    object Home : Screen("home")

    // Game Screen Route with difficulty parameter
    object Game : Screen("game/{difficulty}") {
        fun createRoute(difficulty: String) = "game/$difficulty"
    }

    // End Screen Route with hasWon and difficulty parameters
    object End : Screen("end/{hasWon}/{difficulty}") {
        fun createRoute(hasWon: Boolean, difficulty: String) = "end/$hasWon/$difficulty"
    }

    // Difficulty Selection Screen Route
    object Difficulty : Screen("difficulty")
}