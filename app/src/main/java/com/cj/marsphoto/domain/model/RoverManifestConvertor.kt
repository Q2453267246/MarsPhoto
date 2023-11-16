package com.cj.marsphoto.domain.model

import com.cj.marsphoto.service.model.RoverManifestRemoteModel

fun toUiModel(roverManifestRemoteModel: RoverManifestRemoteModel): RoverManifestUiState =
    RoverManifestUiState.Success(roverManifestRemoteModel.photoManifest.photos.map {
        RoverManifestUiModel(
            sol = it.sol.toString(),
            earthDate = it.earthDate,
            photoNumber = it.totalPhotos.toString()
        )
    }.sorted())
