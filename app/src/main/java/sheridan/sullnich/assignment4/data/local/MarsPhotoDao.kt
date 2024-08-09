package sheridan.sullnich.assignment4.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MarsPhotoDao {
    @Query("SELECT * FROM marsphoto")
    fun getAllMarsPhotoFlow(): Flow<List<LocalMarsPhotos>>

    @Query("SELECT * FROM marsphoto WHERE r_id=:id")
    suspend fun getMarsPhotoById(id: Int): LocalMarsPhotos?

    @Upsert
    suspend fun upsertMarsPhotos(marsPhotos: List<LocalMarsPhotos>)

    @Query("DELETE FROM marsphoto")
    suspend fun deleteAllMarsPhotos()

    @Transaction
    suspend fun refreshMarsPhotos(list: List<LocalMarsPhotos>) {
        deleteAllMarsPhotos()
        upsertMarsPhotos(list)
    }
}