/**
 * MainActivity.kt
 * 
 * This is the main entry point of the Hangman app. It sets up the navigation graph and defines
 * the routes for different screens within the application.
 */

package com.lasalle.hangman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.lasalle.hangman.Navigation.Screen
import com.lasalle.hangman.screens.*
import com.lasalle.hangman.ui.theme.HangManTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HangManTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Initialize NavController
                    val navController = rememberNavController()
                    
                    // Set up NavHost with the NavController and start destination
                    NavHost(navController = navController, startDestination = Screen.Splash.route) {
                        // Splash Screen Route
                        composable(Screen.Splash.route) { SplashScreen(navController) }

                        // Home Screen Route
                        composable(Screen.Home.route) { HomeScreen(navController) }

                        // Difficulty Selection Screen Route
                        composable(Screen.Difficulty.route) { DifficultyScreen(navController) }

                        // Game Screen Route with difficulty parameter
                        composable(
                            route = Screen.Game.route,
                            arguments = listOf(
                                navArgument("difficulty") {
                                    type = NavType.StringType 
                                    defaultValue = "EASY"
                                }
                            )
                        ) { backStackEntry ->
                            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "EASY"
                            GameScreen(navController, difficulty)
                        }

                        // End Screen Route with hasWon and difficulty parameters
                        composable(
                            route = Screen.End.route,
                            arguments = listOf(
                                navArgument("hasWon") {
                                    type = NavType.BoolType
                                    defaultValue = false
                                },
                                navArgument("difficulty") {
                                    type = NavType.StringType
                                    defaultValue = "EASY"
                                }
                            )
                        ) { backStackEntry ->
                            val hasWon = backStackEntry.arguments?.getBoolean("hasWon") ?: false
                            val difficulty = backStackEntry.arguments?.getString("difficulty") ?: "EASY"
                            EndScreen(navController, hasWon, difficulty)
                        }
                    }
                }
            }
        }
    }
}