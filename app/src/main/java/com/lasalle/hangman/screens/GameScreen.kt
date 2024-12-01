package com.lasalle.hangman.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen

@Composable
fun GameScreen(navController: NavController) {
    Button(onClick = { navController.navigate(Screen.End.route) }) {
        Text("End Game")
    }
}