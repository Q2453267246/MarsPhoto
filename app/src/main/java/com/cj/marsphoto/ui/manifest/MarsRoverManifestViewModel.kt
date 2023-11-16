package com.cj.marsphoto.ui.manifest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cj.marsphoto.data.MarsRoverManifestRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MarsRoverManifestViewModel @Inject constructor(
    private val marsRoverManifestRepo: MarsRoverManifestRepo
) : ViewModel() {

    fun getMarsRoverManifest(roverName: String) {
        viewModelScope.launch {
            marsRoverManifestRepo.getMarsRoverManifest(roverName).collect {
                Log.d("TestGetMarsRoverManifest", "getMarsRoverManifest: $it")
            }
        }
    }

}