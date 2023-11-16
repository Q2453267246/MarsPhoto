package com.cj.marsphoto.nav

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.cj.marsphoto.R

object Destinations {
    const val Home = "home"
    const val Manifest = "manifest/{roverName}"
    const val Photo = "photo/{roverName}?sol={sol}"
    const val Saved = "saved"
}

class Action(navController: NavController) {

    val home: () -> Unit = {
        navController.navigate(Destinations.Home)
    }

    val manifest: (roverName: String) -> Unit = { roverName ->
        navController.navigate("manifest/${roverName}")
    }

    val navigationToPhotoScreen: (roverName: String, sol: String) -> Unit = { roverName, sol ->
        navController.navigate("photo/$roverName?sol=$sol")
    }
}

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val drawableId: Int
) {
    object Home : Screen("home", R.string.rover, R.drawable.ic_vip_selected)
    object Saved : Screen("saved", R.string.saved, R.drawable.ic_video_selected)
}
