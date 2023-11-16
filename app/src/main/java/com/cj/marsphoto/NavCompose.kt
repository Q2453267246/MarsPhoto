package com.cj.marsphoto

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cj.marsphoto.nav.Action
import com.cj.marsphoto.nav.Destinations
import com.cj.marsphoto.nav.Screen
import com.cj.marsphoto.ui.theme.MarsPhotoTheme
import com.cj.marsphoto.ui.view.ManifestScreen
import com.cj.marsphoto.ui.view.PhotoListSavedScreen
import com.cj.marsphoto.ui.view.PhotoScreen
import com.cj.marsphoto.ui.view.RoverList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavCompose() {

    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    val items = listOf(
        Screen.Home,
        Screen.Saved
    )

    MarsPhotoTheme {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val navBackStateEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStateEntry?.destination
                    items.forEach { screen ->
                        NavigationBarItem(
                            icon =  {
                                Icon(painter = painterResource(screen.drawableId), contentDescription = stringResource(
                                    id = screen.resourceId
                                ))
                            },
                            label = {
                                Text(text = stringResource(id = screen.resourceId))
                            },
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            selected = currentDestination?.hierarchy?.any {
                                if (it.route?.contains("manifest") == true || it.route?.contains("photo") == true) {
                                    screen.route == "home"
                                } else {
                                    it.route == screen.route
                                }
                            } == true,
                        )
                    }
                }
            }
        ) { innerPadding ->
            val innerModifier = Modifier.padding(innerPadding)
            NavHost(
                navController = navController,
                startDestination = Destinations.Home,
                modifier = innerModifier
            ) {
                composable(Destinations.Home) {
                    RoverList() { roverName ->
                        actions.manifest(roverName)
                    }
                }
                composable(Destinations.Manifest) { backStackEntry ->
                    val roverName = backStackEntry.arguments?.getString("roverName") ?: ""
                    ManifestScreen(roverName, actions.navigationToPhotoScreen)
                }
                composable(Destinations.Photo) { backStackEntry ->
                    val roverName = backStackEntry.arguments?.getString("roverName")
                    val sol = backStackEntry.arguments?.getString("sol")
                    Log.d("TestRovername", "$roverName  -- $sol")
                    PhotoScreen(roverName, sol)
                }
                composable(Destinations.Saved) {
                    PhotoListSavedScreen()
                }
            }
        }
    }

}