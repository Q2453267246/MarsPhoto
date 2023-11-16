package com.cj.marsphoto.ui.photolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.marsphoto.data.MarsPhotoRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsRoverPhotoViewModel @Inject constructor(
    private val marsPhotoRepo: MarsPhotoRepo
) : ViewModel() {


    fun getMarsRoverPhoto(roverName: String, sol: String) {
        viewModelScope.launch {
            marsPhotoRepo.gerMarsPhotos(roverName, sol).collect {

            }
        }
    }
}