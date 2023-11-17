package com.cj.marsphoto.ui.photolist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.marsphoto.data.MarsPhotoRepo
import com.cj.marsphoto.domain.model.RoverPhotoUiModel
import com.cj.marsphoto.domain.model.RoverPhotoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsRoverPhotoViewModel @Inject constructor(
    private val marsPhotoRepo: MarsPhotoRepo
) : ViewModel() {

    private val _roverPhotoUiState:MutableStateFlow<RoverPhotoUiState> = MutableStateFlow(RoverPhotoUiState.Loading)
    val roverPhotoUiState:StateFlow<RoverPhotoUiState>
        get() = _roverPhotoUiState

    fun getMarsRoverPhoto(roverName: String, sol: String) {
        viewModelScope.launch {
            _roverPhotoUiState.value = RoverPhotoUiState.Loading
            marsPhotoRepo.getMarsRoverPhoto(roverName, sol).collect {
                _roverPhotoUiState.value = it
            }
        }
    }

    fun changeSaveStatus(roverPhotoUiModel: RoverPhotoUiModel) {
        Log.d("TestSavedState","${roverPhotoUiModel.isSaved}")
        viewModelScope.launch(Dispatchers.IO) {
            if (!roverPhotoUiModel.isSaved) {
                marsPhotoRepo.savePhoto(roverPhotoUiModel)
            } else {
                marsPhotoRepo.removePhoto(roverPhotoUiModel)
            }
        }
    }

}