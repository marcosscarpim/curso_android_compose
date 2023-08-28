package org.venturus.br.aula14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.venturus.br.aula14.ui.theme.Aula14Theme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aula14Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        state = viewModel.state.value,
                        onButtonClicked = { viewModel.runHeavyWork() }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    state: MainViewState,
    onButtonClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.button_run))
        }

        Text(
            text = stringResource(id = state.messageId),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(color = Color.Black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Aula14Theme {
        MainScreen(state = MainViewState.Idle)
    }
}