package sheridan.sullnich.assignment4.data.local

import android.content.Context
import androidx.room.Room
import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import sheridan.sullnich.assignment4.data.repository.MarsPhotosRepository
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

/*
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .build()


    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): MarsApiService =
        retrofit.create(MarsApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(service: MarsApiService): MarsPhotosRepository =
        NetworkMarsPhotosRepository(service)


}

 */