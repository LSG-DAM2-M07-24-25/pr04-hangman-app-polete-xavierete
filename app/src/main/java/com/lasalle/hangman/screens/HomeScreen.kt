package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lasalle.hangman.R
import com.lasalle.hangman.Navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    // Estado para controlar la visibilidad del diálogo
    val showHelpDialog = remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Hangman!")

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Hangman Logo",
            modifier = Modifier.size(100.dp)
        )

        // Botón de ayuda
        Button(onClick = { showHelpDialog.value = true }) {
            Text("Help")
        }

        // Botones
        Button(onClick = { navController.navigate(Screen.Difficulty.route) }) {
            Text("Difficulty Screen")
        }

        Button(onClick = { navController.navigate(Screen.Game.route) }) {
            Text("Start Game")
        }
    }

    // Diálogo de ayuda
    if (showHelpDialog.value) {
        AlertDialog(
            onDismissRequest = { showHelpDialog.value = false },
            title = { Text("How to Play") },
            text = {
                Column {
                    Text("1. Select a difficulty level")
                    Text("2. Try to guess the hidden word")
                    Text("3. You can make 6 mistakes before losing")
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