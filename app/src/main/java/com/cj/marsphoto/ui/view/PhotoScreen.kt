package com.cj.marsphoto.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cj.marsphoto.domain.model.RoverPhotoUiState
import com.cj.marsphoto.ui.photolist.MarsRoverPhotoViewModel

@Composable
fun PhotoScreen(
    roverName: String?,
    sol: String?,
    marsRoverPhotoViewModel: MarsRoverPhotoViewModel = hiltViewModel()
) {

    val viewState by marsRoverPhotoViewModel.roverPhotoUiState.collectAsStateWithLifecycle()

    if (roverName != null && sol != null) {
        LaunchedEffect(true) {
            marsRoverPhotoViewModel.getMarsRoverPhoto(roverName, sol)
        }
    }
    when (val screenState = viewState) {
        RoverPhotoUiState.Error -> Error()
        RoverPhotoUiState.Loading -> Loading()
        is RoverPhotoUiState.Success -> {
            PhotoList(roverPhotoUiModeList = screenState.roverPhotoUiModelList) {
                marsRoverPhotoViewModel.changeSaveStatus(it)
            }
        }
    }
}

@Preview
@Composable
fun PhotoScreenPreview() {
    PhotoScreen("", "")
}
