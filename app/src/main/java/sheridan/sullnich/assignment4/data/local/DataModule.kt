package sheridan.sullnich.assignment4.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideMarsPhotoDatabase(
        @ApplicationContext applicationContext: Context
    ): MarsPhotoDatabase = Room.databaseBuilder(
        applicationContext, MarsPhotoDatabase::class.java, "marsphoto_database"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMarsPhotoDao(
        database: MarsPhotoDatabase
    ): MarsPhotoDao = database.marsPhotoDao

}