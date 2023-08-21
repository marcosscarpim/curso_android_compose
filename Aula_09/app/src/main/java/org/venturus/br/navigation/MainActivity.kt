package org.venturus.br.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.venturus.br.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStack?.destination?.route ?: AppRoute.PAGE1
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            currentDestination = currentDestination,
                            onDestinationSelected = { newRoute ->
                                navController.navigateSingleTop(newRoute)
                            }
                        )
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = AppRoute.PAGE1
                    ) {
                        composable(AppRoute.PAGE1) { Page1() }
                        composable(AppRoute.PAGE2) { Page2() }
                        composable(AppRoute.PAGE3) { Page3() }
                    }
                }
            }
        }
    }
}

@Composable
fun Page1(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Page 1")
    }
}

@Composable
fun Page2(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Page 2")
    }
}

@Composable
fun Page3(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Page 3")
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentDestination: String,
    onDestinationSelected: (String) -> Unit
) {
    NavigationBar(modifier = modifier.fillMaxWidth()) {
        BOTTOM_BAR_DESTINATIONS.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == destination.route,
                onClick = { onDestinationSelected(destination.route) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}

object AppRoute {
    const val PAGE1 = "page1"
    const val PAGE2 = "page2"
    const val PAGE3 = "page3"
}

data class AppDestination(
    val route: String,
    val icon: ImageVector
)

val BOTTOM_BAR_DESTINATIONS = listOf(
    AppDestination(AppRoute.PAGE1, Icons.Filled.Home),
    AppDestination(AppRoute.PAGE2, Icons.Filled.Star),
    AppDestination(AppRoute.PAGE3, Icons.Filled.Email)
)

fun NavHostController.navigateSingleTop(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        restoreState = true
    }