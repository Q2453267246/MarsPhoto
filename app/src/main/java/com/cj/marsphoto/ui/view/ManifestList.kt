package com.cj.marsphoto.ui.view

import android.util.Log
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cj.marsphoto.R
import com.cj.marsphoto.domain.model.RoverManifestUiModel

@Composable
fun ManifestList(
    roverManifestUiModelList: List<RoverManifestUiModel>,
    roverName: String,
    onClick: (roverName: String, sol: String) -> Unit
) {
    Surface(color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()) {
        val listState = rememberLazyListState()
        LazyColumn(state = listState) {
            items(roverManifestUiModelList.size) {
                Manifest(
                    roverManifestUiModel = roverManifestUiModelList[it],
                    roverName = roverName,
                    onClick = onClick
                )
            }
        }
    }
}

@Composable
fun Manifest(
    roverManifestUiModel: RoverManifestUiModel,
    roverName:String,
    onClick: (roverName: String, sol: String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                Log.d("TestList","${roverManifestUiModel.sol}")
                onClick.invoke(roverName, roverManifestUiModel.sol)
            }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.sol, roverManifestUiModel.sol))
            Text(text = stringResource(id = R.string.earth_date, roverManifestUiModel.earthDate))
            Text(
                text = stringResource(
                    id = R.string.number_of_photo,
                    roverManifestUiModel.photoNumber
                )
            )
        }
    }
}

@Preview
@Composable
fun ManifestPreview() {
    Manifest(
        roverManifestUiModel = RoverManifestUiModel(
            sol = "1000",
            earthDate = "2021-01-01",
            photoNumber = "100"
        ),
        "",
        onClick = { _, _ ->

        }
    )
}