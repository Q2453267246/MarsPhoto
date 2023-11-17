package com.cj.marsphoto.ui.savedlist

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
class MarsRoverSavedViewModel @Inject constructor(
    private val marsPhotoRepo: MarsPhotoRepo
) : ViewModel() {

    private val _marsPhotoUiSavedState:MutableStateFlow<RoverPhotoUiState> = MutableStateFlow(RoverPhotoUiState.Loading)
    val marsPhotoUiSavedState: StateFlow<RoverPhotoUiState>
        get() = _marsPhotoUiSavedState

    fun getAllSaved() {
        viewModelScope.launch(Dispatchers.IO) {
            marsPhotoRepo.getAllSaved().collect {
                _marsPhotoUiSavedState.value = it
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