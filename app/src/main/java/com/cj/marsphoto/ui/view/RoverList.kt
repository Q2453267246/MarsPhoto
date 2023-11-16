package com.cj.marsphoto.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cj.marsphoto.R
import com.cj.marsphoto.domain.model.roverUiModelList

@Composable
fun RoverList() {
    val listState = rememberLazyListState()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(roverUiModelList.size) {
                Rover(
                    name = roverUiModelList[it].name,
                    img = roverUiModelList[it].image,
                    loadingDate = roverUiModelList[it].landingDate,
                    distance = roverUiModelList[it].distance
                )
            }
        }
    }
}

@Composable
@Preview
fun RoverPreview() {
    Rover("Cui jie", R.drawable.test_img_1, "2023-11-11", "1000Km")
}

@Composable
fun Rover(
    name: String,
    img: Int,
    loadingDate: String,
    distance: String
) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                fontWeight = FontWeight.Bold, fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Image(painter = painterResource(id = img), contentDescription = null)
            Text(text = "Credit NASA/JPL", fontSize = 8.sp)
            Text(text = "Loading date: $loadingDate", fontSize = 12.sp)
            Text(text = "Distance traveled: $distance", fontSize = 12.sp)
        }
    }
}