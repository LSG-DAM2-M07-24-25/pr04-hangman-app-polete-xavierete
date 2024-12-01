package com.lasalle.hangman.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen
import com.lasalle.hangman.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    navController: NavController,
    difficulty: String? = "EASY"
) {
    var failCount by remember { mutableStateOf(0) }
    var gameOver by remember { mutableStateOf(false) }
    var guessedLetters by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    
    val wordsByDifficulty = mapOf(
        "EASY" to listOf("CASA", "PERRO", "GATO"),
        "MEDIUM" to listOf("ELEFANTE", "JIRAFA", "LEOPARDO"),
        "HARD" to listOf("RINOCERONTE", "HIPOPOTAMO", "ORANGUTAN")
    )
    
    val currentWord = remember { 
        wordsByDifficulty[difficulty]?.random() ?: wordsByDifficulty["EASY"]!!.random()
    }

    val hangmanImages = remember {
        listOf(
            R.drawable.fail1,
            R.drawable.fail2,
            R.drawable.fail3,
            R.drawable.fail4,
            R.drawable.fail5,
            R.drawable.fail6,
            R.drawable.fail7,
            R.drawable.fail8,
            R.drawable.fail9,
            R.drawable.fail10
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Header
            Text(
                text = "HANGMAN",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            
            Text(
                text = difficulty ?: "EASY",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )

            // Hangman Image
            Card(
                modifier = Modifier
                    .size(220.dp)
                    .padding(vertical = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = if (failCount > 0) hangmanImages[failCount - 1] 
                        else hangmanImages[0]
                    ),
                    contentDescription = "Hangman state $failCount",
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Word Display
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = currentWord.map { 
                            if (guessedLetters.contains(it)) it else '_' 
                        }.joinToString(" "),
                        fontSize = 40.sp,
                        letterSpacing = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = "Intentos restantes: ${10 - failCount}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = when {
                            failCount >= 7 -> Color.Red
                            failCount >= 4 -> Color(0xFFFF9800)
                            else -> Color(0xFF4CAF50)
                        }
                    )
                }
            }

            // Keyboard
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                val keyboard = listOf(
                    "QWERTYUIOP".toList(),
                    "ASDFGHJKLÑ".toList(),
                    "ZXCVBNMPL".toList()
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    keyboard.forEach { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = when(row.size) {
                                7 -> Arrangement.SpaceEvenly    // Para la última fila (ZXCVBNM)
                                10 -> Arrangement.SpaceEvenly   // Para la fila del medio (ASDFGHJKLÑ)
                                else -> Arrangement.SpaceEvenly // Para la primera fila (QWERTYUIOP)
                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Añadimos espaciadores para centrar las filas más cortas
                            if (row.size < 10) {
                                Spacer(modifier = Modifier.weight(0.5f))
                            }
                            
                            row.forEach { letter ->
                                Surface(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .padding(horizontal = 2.dp),
                                    color = if (guessedLetters.contains(letter))
                                        Color.Gray
                                    else
                                        MaterialTheme.colorScheme.primary,
                                    shape = MaterialTheme.shapes.medium,
                                    onClick = {
                                        if (!gameOver && !guessedLetters.contains(letter)) {
                                            if (!currentWord.contains(letter)) {
                                                failCount++
                                            }
                                            guessedLetters = guessedLetters + letter
                                            
                                            val isGameOver = failCount >= 10 || 
                                                            currentWord.all { guessedLetters.contains(it) }
                                            
                                            if (isGameOver) {
                                                gameOver = true
                                                coroutineScope.launch {
                                                    delay(1500)
                                                    navController.navigate(Screen.End.route)
                                                }
                                            }
                                        }
                                    },
                                    enabled = !guessedLetters.contains(letter) && !gameOver
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = letter.toString(),
                                            fontSize = 22.sp,
                                            fontWeight = FontWeight.ExtraBold,
                                            color = if (guessedLetters.contains(letter))
                                                Color.LightGray
                                            else
                                                Color.White,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                            
                            // Añadimos espaciadores para centrar las filas más cortas
                            if (row.size < 10) {
                                Spacer(modifier = Modifier.weight(0.5f))
                            }
                        }
                    }
                }
            }
        }
    }
}