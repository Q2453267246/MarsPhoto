package com.cj.marsphoto.data

import com.cj.marsphoto.domain.model.RoverPhotoUiModel
import com.cj.marsphoto.domain.model.RoverPhotoUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.cj.marsphoto.service.MarsRoverPhotoService
import javax.inject.Inject

class MarsPhotoRepo @Inject constructor(private val marsRoverPhotoService: MarsRoverPhotoService) {
    fun gerMarsPhotos(roverName: String, sol: String): Flow<RoverPhotoUiState> = flow {
        try {
            val networkResult = marsRoverPhotoService.getMarsRoverPhotos(roverName, sol = sol)
            emit(RoverPhotoUiState.Success(networkResult.photos.map { photo ->
                RoverPhotoUiModel(
                    id = photo.id,
                    roverName = photo.rover.name,
                    imgSrc = photo.imgSrc,
                    sol = photo.sol.toString(),
                    earthDate = photo.earthDate,
                    cameraFullName = photo.camera.fullName
                )
            }))
        } catch (throwable: Throwable) {
            emit(RoverPhotoUiState.Error)
        }
    }
}


