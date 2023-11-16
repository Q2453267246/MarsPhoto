package com.cj.marsphoto.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.cj.marsphoto.ui.manifest.MarsRoverManifestViewModel

@Preview
@Composable
fun ManifestScreenPreview() {
//    ManifestScreen("Manifest Screen")
}

@Composable
fun ManifestScreen(
    roverName:String?,
    marsRoverManifestViewModel: MarsRoverManifestViewModel = hiltViewModel()
) {
    if (roverName != null) {
        LaunchedEffect(Unit) {
            marsRoverManifestViewModel.getMarsRoverManifest(roverName)
        }
    }
    Text(text = "Manifest Screen $roverName")
}