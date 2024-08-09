package sheridan.sullnich.assignment4.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marsphoto")
data class LocalMarsPhotos (
    @PrimaryKey
    @ColumnInfo(name = "r_id")
    val id: Int,

    @ColumnInfo(name = "r_title")
    val imgSrc: String,
)