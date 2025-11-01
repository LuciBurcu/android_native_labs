package com.luciburcu.landmark.homescreen.repository

import com.luciburcu.landmark.homescreen.models.Landmark
import java.util.UUID
import kotlin.collections.find
import kotlin.collections.flatten
import kotlin.collections.indexOf

/**
 * Repository class for managing Landmark data.
 * In a real application, this would interface with a database or network layer.
 */
class LandmarkRepository {
    /**
     * In-memory list of landmarks acting as a data source.
     */
    private val _landmarks = mutableListOf(
        Landmark(
            id = "1", name = "Eiffel Tower", description = "An iron lattice tower in Paris"
        ),
        Landmark(
            id = "2", name = "Colosseum", description = "An ancient amphitheater in Rome"
        ),
        Landmark(
            id = "3",
            name = "Great Wall of China",
            description = "A series of fortifications in northern China"
        ),
    )

    /**
     * Gets the list of all landmarks. (part of R in CRUD)
     */
    fun getLandmarks(): List<Landmark> {
        return listOf(_landmarks).flatten()
    }

    /**
     * Gets a landmark by its ID. (part of R in CRUD)
     */
    fun getLandmarkById(id: String): Landmark? {
        return _landmarks.find { it.id == id }
    }

    /**
     * Adds a new landmark. (part of C in CRUD)
     */
    fun addLandmark(name: String, description: String) {
        val newLandmark = Landmark(
            id = UUID.randomUUID().toString(), name = name, description = description
        )
        _landmarks.add(newLandmark)
    }

    /**
     * Deletes a landmark by its ID. (part of D in CRUD)
     */
    fun deleteLandmark(id: String) {
        _landmarks.removeIf { it.id == id }
    }

    /**
     * Updates an existing landmark. (part of U in CRUD)
     */
    fun updateLandmark(id: String, name: String, description: String) {
        val landmark = getLandmarkById(id)
        if (landmark != null) {
            val index = _landmarks.indexOf<Landmark>(landmark)
            _landmarks[index] = Landmark(id, name, description)
        }
    }
}