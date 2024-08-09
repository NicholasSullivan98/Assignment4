package sheridan.sullnich.assignment4.data.remote

import retrofit2.http.GET
import sheridan.sullnich.assignment4.domain.MarsPhoto

interface MarsPhotoApi {
    @GET("photos")
    suspend fun getAllMarsPhotos(): List<RemoteMarsPhoto>
}