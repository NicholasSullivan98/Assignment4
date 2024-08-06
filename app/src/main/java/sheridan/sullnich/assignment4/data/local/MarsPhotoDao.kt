package sheridan.sullnich.assignment4.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
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

    @Update(entity = LocalMarsPhotos::class)
    suspend fun updateIsFavorite(isFavorite: IsFavorite)

    @Update(entity = LocalMarsPhotos::class)
    suspend fun updateIsFavorite(list: List<IsFavorite>)

    @Query("SELECT r_id FROM marsphoto WHERE is_favorite = 1")
    suspend fun getIdsOfFavoriteMarsPhotos(): List<Int>

    @Query("SELECT is_favorite FROM marsphoto WHERE r_id=:id")
    suspend fun getIsFavoriteById(id: Int): Boolean

    @Query("UPDATE marsphoto SET is_favorite=:isFavorite WHERE r_id=:id")
    suspend fun setIsFavoriteById(id: Int, isFavorite: Boolean)

    @Transaction
    suspend fun toggleIsFavoriteById(id: Int) {
        val isFavorite: Boolean = getIsFavoriteById(id)
        setIsFavoriteById(id, !isFavorite)
    }

    @Query("DELETE FROM marsphoto")
    suspend fun deleteAllMarsPhotos()

    @Transaction
    suspend fun refreshMarsPhotos(list: List<LocalMarsPhotos>) {
        deleteAllMarsPhotos()
        upsertMarsPhotos(list)
    }
}