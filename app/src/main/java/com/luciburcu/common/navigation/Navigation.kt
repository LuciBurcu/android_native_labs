package com.luciburcu.common.navigation

import kotlinx.serialization.Serializable

/**
 * We have made this class serializable to facilitate passing it as a parameter in navigation.
 */
@Serializable
object HomeScreenRoute

/**
 * Data class representing the route to the Details Screen with a specific landmark ID.
 * We have made this class serializable to facilitate passing it as a parameter in navigation.
 * We use data classes to hold data in a structured way instead of object or simple classes
 * @param id The unique identifier of the landmark to be displayed on the Details Screen.
 */
@Serializable
data class DetailsScreenRoute(val id: String)

