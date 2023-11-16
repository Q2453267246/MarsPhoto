package com.cj.marsphoto.data

import com.cj.marsphoto.domain.model.RoverManifestUiState
import com.cj.marsphoto.domain.model.toUiModel
import com.cj.marsphoto.service.MarsRoverManifestService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MarsRoverManifestRepo @Inject constructor(
    private val marsRoverManifestService: MarsRoverManifestService
) {

    fun getMarsRoverManifest(roverName:String): Flow<RoverManifestUiState> = flow {
        try {
           toUiModel(marsRoverManifestService.getMarsRoverManifest(roverName.lowercase()))
        } catch (throwable: Throwable) {
            emit(RoverManifestUiState.Error)
        }
    }

}