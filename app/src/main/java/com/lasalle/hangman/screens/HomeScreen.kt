/**
 * HomeScreen.kt
 * 
 * This Composable function represents the Home screen of the Hangman app. It provides users
 * with options to start a new game, view instructions, or exit the application. The screen
 * includes the app logo and welcome text for a friendly user experience.
 */

package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lasalle.hangman.R
import com.lasalle.hangman.Navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    // State to control the visibility of the help dialog
    val showHelpDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color.LightGray,
                        Color.DarkGray
                    )
                )
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo
        Box(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Hangman Logo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillWidth
            )
        }

        // Welcome Text
        Text(
            text = "Welcome to Hangman!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Play Game Button
        Button(
            onClick = { navController.navigate(Screen.Difficulty.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Play Game", fontSize = 18.sp)
        }

        // Instructions Button
        Button(
            onClick = { showHelpDialog.value = true },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Instructions", fontSize = 18.sp)
        }
    }

    // Help Instructions Dialog
    if (showHelpDialog.value) {
        AlertDialog(
            onDismissRequest = { showHelpDialog.value = false },
            title = { Text("How to Play", fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text("1. Select a difficulty level.")
                    Text("2. Try to guess the hidden word one letter at a time.")
                    Text("3. You have a limited number of attempts based on the difficulty.")
                    Text("4. Good luck!")
                }
            },
            confirmButton = {
                TextButton(onClick = { showHelpDialog.value = false }) {
                    Text("Got it!")
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}