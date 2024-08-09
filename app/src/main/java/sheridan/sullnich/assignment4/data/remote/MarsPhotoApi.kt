package sheridan.sullnich.assignment4.data.remote

import retrofit2.http.GET

interface MarsPhotoApi {
    @GET("photos")
    suspend fun getAllMarsPhotos(): List<RemoteMarsPhoto>
}