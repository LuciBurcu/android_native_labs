package com.luciburcu.landmark.homescreen.models

/**
Base data class for Landmark data model
We use data classes to hold data in a structured way and to have access to
useful methods like `copy()`, `toString()`, and `equals()`.
 */
data class Landmark(
    val id: String,
    val name: String,
    val description: String,
)