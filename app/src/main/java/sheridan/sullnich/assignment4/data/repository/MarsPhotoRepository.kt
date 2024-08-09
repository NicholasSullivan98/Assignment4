package sheridan.sullnich.assignment4.data.repository

import kotlinx.coroutines.flow.Flow
import sheridan.sullnich.assignment4.domain.MarsPhoto

interface MarsPhotoRepository {

    fun getAllMarsPhotosFlow(): Flow<List<MarsPhoto>>

    suspend fun getMarsPhotoById(id: Int): MarsPhoto?

    suspend fun toggleIsFavoriteById(id: Int)

    suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean)

    suspend fun refreshMarsPhotos()

    suspend fun clearDatabase()
}