package com.luciburcu.landmark.homescreen.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.luciburcu.common.navigation.DetailsScreenRoute
import com.luciburcu.landmark.homescreen.components.LandmarkListItem
import com.luciburcu.landmark.homescreen.repository.LandmarkRepository
import kotlin.collections.flatten
import kotlin.let

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(landmarkRepository: LandmarkRepository, navController: NavHostController) {
    var landmarks by remember { mutableStateOf(landmarkRepository.getLandmarks()) }
    var shouldShowBottomSheet by remember { mutableStateOf(false) }
    var idToDelete by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ), title = { Text("Homepage") })
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(items = landmarks) { it ->
                    LandmarkListItem(it, onLongClick = {
                        shouldShowBottomSheet = true
                        idToDelete = it.id
                    }) {
                        navController.navigate(DetailsScreenRoute(id = it.id))
                    }
                }
            }
            if (shouldShowBottomSheet) {
                ModalBottomSheet(onDismissRequest = { shouldShowBottomSheet = false }) {
                    Button(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        onClick = {
                            idToDelete?.let { landmarkRepository.deleteLandmark(it) }
                            landmarks = listOf(landmarkRepository.getLandmarks()).flatten()
                            shouldShowBottomSheet = false
                        },
                        colors = ButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red,
                            disabledContentColor = Color.LightGray,
                            disabledContainerColor = Color.White
                        )
                    ) {
                        Text("Delete Landmark")
                    }
                }
            }
        }
    }
}