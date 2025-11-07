package com.luciburcu.landmark.homescreen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luciburcu.landmark.homescreen.models.Landmark
import com.luciburcu.landmark.homescreen.repository.LandmarkRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val landmarkRepository: LandmarkRepository) : ViewModel() {
    /**
     * UI state variables
     * We use `remember` so that the state is preserved across recompositions
     * We use `by` delegate to simplify state access
     */
    var landmarks by mutableStateOf(listOf<Landmark>())
    var shouldShowBottomSheet by mutableStateOf(false)
    var idToDelete by mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)

    var isError by mutableStateOf(false)

    init {
        // Launch a coroutine to fetch landmarks when the ViewModel is created
        // This ensures that data fetching is done off the main thread
        viewModelScope.launch {
            getItems()
        }
    }

    suspend fun getItems() {
        isLoading = true
        try {
            landmarks = landmarkRepository.getLandmarks()
        } catch (e: Exception) {
            isError = true
        } finally {
            isLoading = false

        }
    }

    fun deleteLandmarkById(id: String?) {
        id?.let {
            landmarkRepository.deleteLandmark(id)
            // Refresh the list after deletion
            // This will introduce a slight delay as getItems is a suspend function and invokes `isLoading`
            viewModelScope.launch {
                getItems()
            }
        }
    }

    fun showBottomSheet() {
        shouldShowBottomSheet = true
    }

    fun hideBottomSheet() {
        shouldShowBottomSheet = false
    }
}