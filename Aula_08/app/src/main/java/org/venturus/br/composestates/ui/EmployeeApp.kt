package org.venturus.br.composestates.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun EmployeeApp(
    modifier: Modifier = Modifier,
    employeeHomeUIState: EmployeeHomeUIState
) {
    var selectedDestination by remember { mutableStateOf(AppRoute.EMPLOYEES) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        if (selectedDestination == AppRoute.EMPLOYEES) {
            EmployeeListScreen(
                modifier = Modifier.weight(1f),
                employeeHomeUIState = employeeHomeUIState
            )
        } else {
            EmptyComingSoon(modifier = Modifier.weight(1f))
        }

        NavigationBar(modifier = Modifier.fillMaxWidth()) {
            TOP_LEVEL_DESTINATIONS.forEach { destination ->
                NavigationBarItem(
                    selected = selectedDestination == destination.route,
                    onClick = { selectedDestination = destination.route },
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
}

object AppRoute {
    const val EMPLOYEES = "Employees"
    const val STAR = "Star"
    const val EMAIL = "Email"
}

data class AppDestination(
    val route: String,
    val icon: ImageVector
)

val TOP_LEVEL_DESTINATIONS = listOf(
    AppDestination(AppRoute.EMPLOYEES, Icons.Default.Person),
    AppDestination(AppRoute.STAR, Icons.Default.Star),
    AppDestination(AppRoute.EMAIL, Icons.Default.Email)
)