/**
 * DifficultyScreen.kt
 * 
 * This Composable function displays the Difficulty Selection screen where users can choose
 * the desired difficulty level for the Hangman game. Each difficulty level is associated
 * with a specific color for better visual distinction.
 */

package com.lasalle.hangman.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen

@Composable
fun DifficultyScreen(navController: NavController) {
    // MARK: - UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Padding around the screen
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title for Difficulty Selection
        Text(
            "Select Difficulty",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // List of difficulties with associated colors
        val difficulties = listOf(
            "VERY EASY" to Color(0xFF81C784),   // Light Green
            "EASY" to Color(0xFF4CAF50),        // Green
            "MEDIUM" to Color(0xFFFFA726),      // Orange
            "HARD" to Color(0xFFD32F2F),        // Red
            "VERY HARD" to Color(0xFF6A1B9A)    // Purple
        )

        // Display buttons for each difficulty
        difficulties.forEach { (difficulty, color) ->
            Button(
                onClick = { 
                    // Navigate to GameScreen with selected difficulty
                    navController.navigate(Screen.Game.createRoute(difficulty))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = color // Set button color based on difficulty
                )
            ) {
                Text(
                    text = difficulty,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}