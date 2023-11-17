package com.cj.marsphoto.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cj.marsphoto.domain.model.RoverPhotoUiState
import com.cj.marsphoto.domain.model.roverUiModelList
import com.cj.marsphoto.ui.savedlist.MarsRoverSavedViewModel

@Composable
fun PhotoListSavedScreen(
    modifier: Modifier = Modifier,
    marsRoverSavedViewModel: MarsRoverSavedViewModel = hiltViewModel()
) {
    val viewState by marsRoverSavedViewModel.marsPhotoUiSavedState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        marsRoverSavedViewModel.getAllSaved()
    }
    val state = rememberLazyListState()
    when (viewState) {
        RoverPhotoUiState.Error -> Error()
        RoverPhotoUiState.Loading -> Loading()
        is RoverPhotoUiState.Success -> PhotoList(
            roverPhotoUiModeList = (viewState as RoverPhotoUiState.Success).roverPhotoUiModelList,
            onClick = {
                marsRoverSavedViewModel.changeSaveStatus(it)
            }
        )
    }
}

@Composable
@Preview
fun PhotoListSavedScreenPreview() {

}
