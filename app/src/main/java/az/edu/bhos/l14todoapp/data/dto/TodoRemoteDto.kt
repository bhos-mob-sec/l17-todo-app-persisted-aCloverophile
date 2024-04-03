package az.edu.bhos.l14todoapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TodoRemoteDto(
    val id: String,
    val title: String,
    val completed: Boolean,
    val weekday: String
) {
    fun toLocal() = TodoLocalDto(
        id = id,
        title = title,
        completed = completed,
        weekday = weekday
    )
}