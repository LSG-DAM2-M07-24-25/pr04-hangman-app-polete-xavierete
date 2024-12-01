package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    hasWon: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        if (hasWon) Color(0xFFE1F5FE) else Color(0xFFFFEBEE),
                        if (hasWon) Color(0xFF81D4FA) else Color(0xFFFFCDD2)
                    )
                )
            )
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Result Image
        Image(
            painter = painterResource(
                id = if (hasWon) R.drawable.you_win else R.drawable.you_lose
            ),
            contentDescription = if (hasWon) "Victory image" else "Defeat image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )
        
        // Result Text
        Text(
            text = if (hasWon) "¡Felicidades!" else "¡Mejor suerte la próxima vez!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (hasWon) Color(0xFF2196F3) else Color(0xFFE57373),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Return Home Button
        Button(
            onClick = { navController.navigate(Screen.Home.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (hasWon) Color(0xFF2196F3) else Color(0xFFE57373)
            )
        ) {
            Text(
                "Volver al Menú",
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}