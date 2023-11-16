package com.cj.marsphoto.service

import com.cj.marsphoto.BuildConfig
import com.cj.marsphoto.service.model.RoverPhotoRemoteModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarsRoverPhotoService {

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
        private const val API_KEY = "cDLR0kSxLwDd2cWotqzTsD9NJ3K9W8pRna6QDznS"

        fun create(): MarsRoverPhotoService {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
            }
            val client = OkHttpClient.Builder().apply {
                addInterceptor(logger)
            }.build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarsRoverPhotoService::class.java)
        }

    }


    @GET("mars-photos/api/v1/rovers/{rover_name}/photos?api_key=${API_KEY}")
    suspend fun getMarsRoverPhotos(
        @Path("rover_name") roverName: String,
        @Query("sol") sol: String
    ): RoverPhotoRemoteModel

}