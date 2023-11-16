package com.cj.marsphoto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cj.marsphoto.nav.Action
import com.cj.marsphoto.nav.Destinations
import com.cj.marsphoto.ui.theme.MarsPhotoTheme
import com.cj.marsphoto.ui.view.ManifestScreen
import com.cj.marsphoto.ui.view.RoverList

@Composable
fun NavCompose() {

    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }

    MarsPhotoTheme {
        NavHost(navController = navController, startDestination = Destinations.Home) {
            composable(Destinations.Home) {
                RoverList() { roverName ->
                    actions.manifest(roverName)
                }
            }
            composable(Destinations.Manifest) { backStackEntry ->
                val roverName = backStackEntry.arguments?.getString("roverName") ?: ""
                ManifestScreen(roverName)
            }
        }
    }

}