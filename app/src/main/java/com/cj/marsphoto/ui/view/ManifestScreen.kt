package com.cj.marsphoto.ui.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ManifestScreenPreview() {
    ManifestScreen("Manifest Screen")
}

@Composable
fun ManifestScreen(
    roverName:String
) {
    Text(text = "Manifest Screen $roverName")
}