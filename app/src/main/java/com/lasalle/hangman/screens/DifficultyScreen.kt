package com.lasalle.hangman.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lasalle.hangman.Navigation.Screen
import androidx.compose.material3.*
import com.lasalle.hangman.model.difficulties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyScreen(navController: NavController) {
    var selectedDifficulty by remember { mutableStateOf(difficulties[0]) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Select Difficulty",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedDifficulty.name,
                onValueChange = { },
                label = { Text("Difficulty") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                difficulties.forEach { difficulty ->
                    DropdownMenuItem(
                        text = { Text(difficulty.name) },
                        onClick = {
                            selectedDifficulty = difficulty
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Difficulty: ${selectedDifficulty.name}", color = selectedDifficulty.color)
        Text("Description: ${selectedDifficulty.description}")
        Text("Comparison: ${selectedDifficulty.comparison}")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate(Screen.Home.route) }) {
            Text("Return Home")
        }
    }
}

@Preview
@Composable
fun DifficultyScreenPreview() {
    DifficultyScreen(navController = NavController(LocalContext.current))
}