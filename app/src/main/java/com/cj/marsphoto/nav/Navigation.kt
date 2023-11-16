package com.cj.marsphoto.nav

import androidx.navigation.NavController

object Destinations {
    const val Home = "home"
    const val Manifest = "manifest/{roverName}"
    const val Photo = "photo/{roverName}?sol={sol}"
}

class Action(navController: NavController) {

    val home: () -> Unit = {
        navController.navigate(Destinations.Home)
    }

    val manifest: (roverName: String) -> Unit = { roverName ->
        navController.navigate("manifest/${roverName}")
    }

    val navigationToPhotoScreen: (roverName: String, sol: String) -> Unit = { roverName, sol ->
        navController.navigate("${Destinations.Photo}/$roverName?sol=$sol")
    }
}
