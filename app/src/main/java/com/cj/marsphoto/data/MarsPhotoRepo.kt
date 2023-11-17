package com.cj.marsphoto.data

import android.util.Log
import com.cj.marsphoto.db.MarsRoverSavedPhotoDao
import com.cj.marsphoto.domain.model.RoverPhotoUiModel
import com.cj.marsphoto.domain.model.RoverPhotoUiState
import com.cj.marsphoto.domain.model.toDbModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.cj.marsphoto.service.MarsRoverPhotoService
import com.cj.marsphoto.service.model.RoverPhotoRemoteModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class MarsPhotoRepo @Inject constructor(
    private val marsRoverPhotoService: MarsRoverPhotoService,
    private val marsRoverSavedPhotoDao: MarsRoverSavedPhotoDao
) {

    fun getMarsPhotos(roverName: String, sol: String): Flow<RoverPhotoRemoteModel?> = flow {
        try {
            val networkResult = marsRoverPhotoService.getMarsRoverPhotos(
                roverName,sol
            )
            emit(networkResult)
        } catch (throwable:Throwable) {
            emit(null)
        }
    }

    fun getMarsRoverPhoto(roverName: String,sol: String) :Flow<RoverPhotoUiState> =
        marsRoverSavedPhotoDao.allSavedIds(roverName = roverName,sol = sol).combine(
            getMarsPhotos(roverName,sol)
        ) { local, remote ->
            Log.d("TestLocal","${local.toString()} $roverName $sol")
            remote?.let { roverPhotoRemoteModel ->
                RoverPhotoUiState.Success(
                    roverPhotoRemoteModel.photos.map { photo ->
                        RoverPhotoUiModel(
                            id = photo.id,
                            roverName = photo.rover.name,
                            imgSrc = photo.imgSrc,
                            sol = photo.sol.toString(),
                            earthDate = photo.earthDate,
                            cameraFullName = photo.camera.fullName,
                            isSaved = local.contains(photo.id)
                        )
                    }
                )
            } ?: run {
                RoverPhotoUiState.Error
            }
        }

    suspend fun savePhoto(roverPhotoUiModel: RoverPhotoUiModel) {
        marsRoverSavedPhotoDao.insert(toDbModel(roverPhotoUiModel))
    }

    suspend fun removePhoto(roverPhotoUiModel: RoverPhotoUiModel) {
        marsRoverSavedPhotoDao.delete(toDbModel(roverPhotoUiModel))
    }

}


