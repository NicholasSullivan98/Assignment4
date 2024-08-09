package sheridan.sullnich.assignment4.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMarsPhoto(
    val id: Int,
    @SerialName(value = "img_src")
    val imgSrc: String,
)