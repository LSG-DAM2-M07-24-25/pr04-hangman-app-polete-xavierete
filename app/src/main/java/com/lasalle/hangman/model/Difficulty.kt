package com.lasalle.hangman.model

import androidx.compose.ui.graphics.Color

data class Difficulty(
    val name: String,
    val color: Color,
    val description: String,
    val comparison: String
)

val difficulties = listOf(
    Difficulty(
        name = "Cheerios",
        color = Color.Yellow,
        description = "Easy peasy, like eating Cheerios.",
        comparison = "Simpler than Froot Loops, but harder than nothing."
    ),
    Difficulty(
        name = "Froot Loops",
        color = Color.Red,
        description = "A bit more challenging, like navigating a bowl of Froot Loops.",
        comparison = "Simpler than Lucky Charms, but harder than Cheerios."
    ),
    Difficulty(
        name = "Lucky Charms",
        color = Color.Green,
        description = "Things are getting magical, but tricky like finding the marshmallows in Lucky Charms.",
        comparison = "Simpler than Honey Nut Cheerios, but harder than Froot Loops."
    ),
    Difficulty(
        name = "Honey Nut Cheerios",
        color = Color.Blue,
        description = "Sweet and challenging, like resisting a box of Honey Nut Cheerios.",
        comparison = "Simpler than Reese's Puffs, but harder than Lucky Charms."
    ),
    Difficulty(
        name = "Reese's Puffs",
        color = Color.DarkGray,
        description = "The ultimate challenge, like finishing a bowl of Reese's Puffs without milk.",
        comparison = "The hardest difficulty, nothing is harder."
    )
)