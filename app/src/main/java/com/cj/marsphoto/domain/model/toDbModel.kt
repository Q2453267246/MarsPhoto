package com.cj.marsphoto.domain.model

import com.cj.marsphoto.db.MarsRoverSavedLocalModel
import java.util.Locale

fun toDbModel(roverPhotoUiModel: RoverPhotoUiModel) :MarsRoverSavedLocalModel =
    MarsRoverSavedLocalModel(
        roverPhotoId = roverPhotoUiModel.id,
        roverName = roverPhotoUiModel.roverName.lowercase(Locale.ROOT),
        imgSrc = roverPhotoUiModel.imgSrc,
        sol = roverPhotoUiModel.sol,
        earthDate = roverPhotoUiModel.earthDate,
        cameraFullName = roverPhotoUiModel.cameraFullName
    )

fun toUiModel(roverPhotoLocalModel: List<MarsRoverSavedLocalModel>) :List<RoverPhotoUiModel> =
    roverPhotoLocalModel.map { model ->
        RoverPhotoUiModel(
            id = model.roverPhotoId,
            roverName = model.roverName,
            imgSrc = model.imgSrc,
            sol = model.sol,
            earthDate = model.earthDate,
            cameraFullName = model.cameraFullName,
            isSaved = true
        )
    }
