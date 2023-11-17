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