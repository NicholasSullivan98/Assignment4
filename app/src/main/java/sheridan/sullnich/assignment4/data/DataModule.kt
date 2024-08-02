package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

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