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
import com.luciburcu.landmark.homescreen.viewmodel.HomeScreenViewModel
import kotlin.collections.flatten
import kotlin.let

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ), title = { Text("Homepage") })
        }, modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // This is optional loading state handling, you can do it even nicer with a proper loading
        // component and embedding it in the column below
        if (homeScreenViewModel.isLoading) {
            Text("Loading...", modifier = Modifier.padding(innerPadding))
            return@Scaffold
        }
        Column(modifier = Modifier.padding(innerPadding)) {
            LazyColumn {
                items(items = homeScreenViewModel.landmarks) { it ->
                    LandmarkListItem(it, onLongClick = {
                        homeScreenViewModel.showBottomSheet()
                        homeScreenViewModel.idToDelete = it.id
                    }) {
                        navController.navigate(DetailsScreenRoute(id = it.id))
                    }
                }
            }
            if (homeScreenViewModel.shouldShowBottomSheet) {
                ModalBottomSheet(onDismissRequest = {
                    homeScreenViewModel.hideBottomSheet()
                }) {
                    Button(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        onClick = {
                            homeScreenViewModel.deleteLandmarkById(homeScreenViewModel.idToDelete)
                            homeScreenViewModel.shouldShowBottomSheet = false
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