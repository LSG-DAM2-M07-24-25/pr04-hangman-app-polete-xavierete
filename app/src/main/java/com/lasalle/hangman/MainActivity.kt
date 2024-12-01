package com.lasalle.hangman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lasalle.hangman.Navigation.Screen
import com.lasalle.hangman.screens.DifficultyScreen
import com.lasalle.hangman.screens.EndScreen
import com.lasalle.hangman.screens.GameScreen
import com.lasalle.hangman.screens.HomeScreen
import com.lasalle.hangman.screens.SplashScreen
import com.lasalle.hangman.ui.theme.HangManTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangManTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Splash.route
                    ) {
                        composable(Screen.Splash.route) { SplashScreen(navController) }
                        composable(Screen.Home.route) { HomeScreen(navController) }
                        composable(
                            route = Screen.Game.route,
                            arguments = listOf(
                                navArgument("difficulty") { 
                                    type = NavType.StringType 
                                    defaultValue = "EASY"
                                }
                            )
                        ) { backStackEntry ->
                            val difficulty = backStackEntry.arguments?.getString("difficulty")
                            GameScreen(navController, difficulty)
                        }
                        composable(Screen.End.route) { EndScreen(navController) }
                        composable(Screen.Difficulty.route) { DifficultyScreen(navController) }
                    }
                }
            }
        }
    }
}