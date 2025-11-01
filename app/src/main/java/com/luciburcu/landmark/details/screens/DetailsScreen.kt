package com.luciburcu.landmark.details.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.luciburcu.landmark.homescreen.repository.LandmarkRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    landmarkRepository: LandmarkRepository, navController: NavHostController, id: String
) {
    val landmark = landmarkRepository.getLandmarkById(id = id)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ), title = { Text("Details") }, navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Navigate back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                })
        }, modifier = Modifier.fillMaxSize()
    ) { it ->
        Column(modifier = Modifier.padding(it)) {
            Text("Landmark name: ${landmark?.name ?: "Not found"}")
            Text("Landmark description: ${landmark?.description ?: "No description"}")
        }
    }
}