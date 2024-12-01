package com.lasalle.hangman.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen

@Composable
fun DifficultyScreen(navController: NavController) {
    var selectedDifficulty by remember { mutableStateOf("EASY") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Select Difficulty",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = { 
                navController.navigate(Screen.Game.createRoute(selectedDifficulty))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Easy")
        }

        Button(
            onClick = { 
                navController.navigate(Screen.Game.createRoute("MEDIUM"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Medium")
        }

        Button(
            onClick = { 
                navController.navigate(Screen.Game.createRoute("HARD"))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Hard")
        }
    }
}