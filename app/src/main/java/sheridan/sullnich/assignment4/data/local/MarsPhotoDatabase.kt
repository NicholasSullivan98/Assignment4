package sheridan.sullnich.assignment4.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalMarsPhotos::class], version = 2, exportSchema = false
)
abstract class MarsPhotoDatabase : RoomDatabase() {
    abstract val marsPhotoDao: MarsPhotoDao
}