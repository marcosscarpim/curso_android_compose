package org.venturus.br.composestates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.venturus.br.composestates.data.EmployeeDataProvider
import org.venturus.br.composestates.ui.EmployeeApp
import org.venturus.br.composestates.ui.EmployeeHomeUIState
import org.venturus.br.composestates.ui.EmployeeHomeViewModel
import org.venturus.br.composestates.ui.theme.ComposeStatesTheme

class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStatesTheme {
                val uiState by viewModel.uiState.collectAsState()

                EmployeeApp(employeeHomeUIState = uiState)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeStatesTheme {
        EmployeeApp(
            employeeHomeUIState = EmployeeHomeUIState(
                employees = EmployeeDataProvider.allEmployees
            )
        )
    }
}