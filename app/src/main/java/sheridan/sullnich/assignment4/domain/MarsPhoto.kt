package sheridan.sullnich.assignment4.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: Int,
    @SerialName(value = "img_src")
    val imgSrc: String,
    //val description: String,
    val isFavorite: Boolean = false
)