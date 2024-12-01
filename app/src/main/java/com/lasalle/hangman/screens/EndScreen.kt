/**
 * EndScreen.kt
 * 
 * This Composable function represents the Result screen displayed at the end of the game.
 * It shows whether the user has won or lost and provides options to return to the home
 * menu or play again. Additionally, it includes a hints system that unlocks based on
 * the selected difficulty level.
 */

package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.lasalle.hangman.R
import com.lasalle.hangman.Navigation.Screen

@Composable
fun EndScreen(
    navController: NavController,
    hasWon: Boolean = false,
    difficulty: String = "EASY" // Added difficulty parameter to determine hints
) {
    // MARK: - Determine Number of Hints Based on Difficulty
    val hintsAvailable = when (difficulty.uppercase()) {
        "MEDIUM" -> 1
        "HARD" -> 2
        "VERY HARD" -> 3
        else -> 0 // No hints for VERY EASY and EASY
    }

    // MARK: - Background Gradient Colors based on game result
    val backgroundColors = if (hasWon) {
        listOf(Color.White, Color(0xFFE1F5FE), Color(0xFF81D4FA)) // Victory Colors
    } else {
        listOf(Color.White, Color(0xFFFFEBEE), Color(0xFFFFCDD2)) // Defeat Colors
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = backgroundColors
                )
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // MARK: - Display Victory or Defeat Image with Rounded Corners
        Box(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp)) // Added rounded corners to the Box
        ) {
            Image(
                painter = painterResource(
                    id = if (hasWon) R.drawable.you_win else R.drawable.you_lose
                ),
                contentDescription = if (hasWon) "Victory image" else "Defeat image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp)), // Ensures the Image respects the Box's rounded corners
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
        }

        // MARK: - Display Result Text
        Text(
            text = if (hasWon) "Congratulations!" else "Better luck next time!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (hasWon) Color(0xFF2196F3) else Color(0xFFE57373),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // MARK: - Display Hints Information if Available
        if (hintsAvailable > 0) {
            Text(
                text = "You have $hintsAvailable hint(s) available for your next game!",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // MARK: - Return Home Button
        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (hasWon) Color(0xFF2196F3) else Color(0xFFE57373)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "Return to Menu",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        // MARK: - Play Again Button with Hints Unlock
        if (hintsAvailable > 0) {
            Button(
                onClick = { 
                    // Navigate back to GameScreen with the same difficulty
                    navController.navigate(Screen.Game.createRoute(difficulty))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50) // Green color for Play Again
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    "Play Again with Hints",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}