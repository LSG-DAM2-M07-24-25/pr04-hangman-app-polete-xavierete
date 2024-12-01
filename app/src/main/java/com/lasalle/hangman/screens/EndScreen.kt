package com.lasalle.hangman.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen

@Composable
fun EndScreen(navController: NavController) {
    Button(onClick = {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route) { inclusive = true }
        }
    }) {
        Text("Back to Home")
    }
}