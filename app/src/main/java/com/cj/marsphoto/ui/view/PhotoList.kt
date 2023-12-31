package com.cj.marsphoto.ui.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cj.marsphoto.R
import com.cj.marsphoto.domain.model.RoverPhotoUiModel

@Composable
fun PhotoList(
    roverPhotoUiModeList: List<RoverPhotoUiModel>,
    onClick:(roverPhotoUiModel: RoverPhotoUiModel) -> Unit = {}
) {
    val listState = rememberLazyListState()
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(roverPhotoUiModeList.size) {
                Photo(roverPhotoUiModeList[it],onClick = onClick)
            }
        }
    }
}

@Composable
fun Photo(
    roverPhotoUiModel: RoverPhotoUiModel,
    onClick:(roverPhotoUiModel: RoverPhotoUiModel) -> Unit = {}
) {
    Card(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = if (roverPhotoUiModel.isSaved) R.drawable.ic_selfchoose_selected else R.drawable.ic_video_selected),
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onClick.invoke(roverPhotoUiModel)
                    }
                )
                Text(text = roverPhotoUiModel.roverName, modifier = Modifier.padding(16.dp))
            }
            AsyncImage(
                model = roverPhotoUiModel.imgSrc,
                contentDescription = null,
                modifier = Modifier.height(300.dp)
            )
            Text(text = stringResource(id = R.string.sol, roverPhotoUiModel.sol))
            Text(text = stringResource(id = R.string.earth_date, roverPhotoUiModel.earthDate))
            Text(text = roverPhotoUiModel.cameraFullName)
        }
    }
}

@Preview
@Composable
fun PhotoListPreview() {
    Photo(
        roverPhotoUiModel = RoverPhotoUiModel(
            4,
            roverName = "Curiosity",
            imgSrc = "https://domain.com",
            sol = "3001",
            earthDate = "2021-01-01",
            cameraFullName = "Mast Camera",
            isSaved = true
        )
    )
}