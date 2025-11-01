package com.luciburcu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.luciburcu.common.navigation.DetailsScreenRoute
import com.luciburcu.common.navigation.HomeScreenRoute
import com.luciburcu.common.theme.Android_native_labsTheme
import com.luciburcu.landmark.details.screens.DetailsScreen
import com.luciburcu.landmark.homescreen.repository.LandmarkRepository
import com.luciburcu.landmark.homescreen.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val controller = rememberNavController()
            val landmarkRepository = LandmarkRepository()
            Android_native_labsTheme {
                NavHost(navController = controller, startDestination = HomeScreenRoute) {
                    composable<HomeScreenRoute> {
                        HomeScreen(
                            landmarkRepository = landmarkRepository,
                            navController = controller
                        )
                    }
                    composable<DetailsScreenRoute> {
                        val args = it.toRoute<DetailsScreenRoute>()
                        DetailsScreen(
                            landmarkRepository = landmarkRepository,
                            navController = controller,
                            id = args.id
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_native_labsTheme {
        Greeting("Android")
    }
}