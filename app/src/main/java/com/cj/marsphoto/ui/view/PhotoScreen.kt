package com.cj.marsphoto.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cj.marsphoto.ui.photolist.MarsRoverPhotoViewModel

@Composable
fun PhotoScreen(
    roverName: String?,
    sol: String?,
    marsRoverPhotoViewModel: MarsRoverPhotoViewModel = hiltViewModel()
) {
    if (roverName != null && sol != null) {
        LaunchedEffect(true) {
            marsRoverPhotoViewModel.getMarsRoverPhoto(roverName, sol)
        }
    }
    Text(text = "Photo Screen")
}

@Preview
@Composable
fun PhotoScreenPreview() {
    PhotoScreen("", "")
}
