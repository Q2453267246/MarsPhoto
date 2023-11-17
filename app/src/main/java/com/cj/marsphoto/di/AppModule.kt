package com.cj.marsphoto.di

import android.content.Context
import com.cj.marsphoto.db.MarsRoverSavedDatabase
import com.cj.marsphoto.db.MarsRoverSavedPhotoDao
import com.cj.marsphoto.service.MarsRoverManifestService
import com.cj.marsphoto.service.MarsRoverPhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMarsRoverManifestService(): MarsRoverManifestService {
        return MarsRoverManifestService.create()
    }

    @Provides
    fun provideMarsPhotoService(): MarsRoverPhotoService {
        return MarsRoverPhotoService.create()
    }

    @Provides
    fun provideMarsRoverPhotoDao(@ApplicationContext context: Context): MarsRoverSavedPhotoDao =
        MarsRoverSavedDatabase.getInstance(context).marsRoverSavedPhotoDao()

}