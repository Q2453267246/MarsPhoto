package com.cj.marsphoto.di

import com.cj.marsphoto.service.MarsRoverManifestService
import com.cj.marsphoto.service.MarsRoverPhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}