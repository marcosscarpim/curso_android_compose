package org.venturus.br.notifications

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.venturus.br.notifications.ui.theme.NotificationsTheme

class MainActivity : ComponentActivity() {

    private val mainNotification = MainNotification()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainNotification.createNotificationChannel(this@MainActivity)

        setContent {
            NotificationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val postNotificationPermission =
                        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

                    LaunchedEffect(key1 = true) {
                        if (!postNotificationPermission.hasPermission) {
                            postNotificationPermission.launchPermissionRequest()
                        }
                    }

                    NotificationScreen(onNotificationClicked = {
                        mainNotification.showNotification(this@MainActivity)
                    })
                }
            }
        }
    }
}

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    onNotificationClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = { onNotificationClicked() }
        ) {
            Text(text = "Mostrar notificação")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotificationsTheme {
        NotificationScreen()
    }
}