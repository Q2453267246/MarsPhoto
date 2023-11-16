package com.cj.marsphoto.ui.view

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cj.marsphoto.domain.model.RoverManifestUiState
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

    val viewState by marsRoverManifestViewModel.roverManifestUiState.collectAsStateWithLifecycle()

    if (roverName != null) {
        LaunchedEffect(Unit) {
            marsRoverManifestViewModel.getMarsRoverManifest(roverName)
        }
    }
    LaunchedEffect(viewState) {
        Log.d("TestViewState","$viewState")
    }
    when (val roverManifestUiState = viewState) {
        RoverManifestUiState.Error -> Error()
        RoverManifestUiState.Loading -> Loading()
        is RoverManifestUiState.Success -> ManifestList(roverManifestUiModelList = roverManifestUiState.roverManifestUiModel)
    }
}