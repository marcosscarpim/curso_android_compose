package org.venturus.br.navigation

import android.annotation.SuppressLint
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import org.venturus.br.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                var currentDestination by remember { mutableStateOf(AppRoute.PAGE1) }
                Scaffold(
                    bottomBar = {
                        BottomBar(
                            currentDestination = currentDestination,
                            onDestinationSelected = { currentDestination = it }
                        )
                    }
                ) { innerPadding ->
                    val pageModifier = Modifier.padding(innerPadding)
                    when (currentDestination) {
                        AppRoute.PAGE1 -> Page1(pageModifier)
                        AppRoute.PAGE2 -> Page2(pageModifier)
                        AppRoute.PAGE3 -> Page3(pageModifier)
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