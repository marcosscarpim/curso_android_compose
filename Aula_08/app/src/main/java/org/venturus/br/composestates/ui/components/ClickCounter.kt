package org.venturus.br.composestates.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.venturus.br.composestates.ui.theme.ComposeStatesTheme

@Composable
fun ClickCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    var rememberReset by rememberSaveable { mutableStateOf(true) }

    Column(modifier = modifier.padding(16.dp)) {
        Text("You've clicked $count times")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { count++ }) {
            Text("Click me")
        }
        Spacer(Modifier.height(16.dp))
        if (count > 0) {

            if (rememberReset) {
                Button(onClick = {
                    rememberReset = false
                    count = 0
                }) {
                    Text("Reset")
                }
            }
        }
    }
}

@Composable
fun StatefulClickCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(
        modifier = modifier,
        count = count,
        onCountChanged = { count++ }
    )
}

@Composable
fun StatelessCounter(
    modifier: Modifier = Modifier,
    count: Int,
    onCountChanged: () -> Unit
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("You've clicked $count times")
        Spacer(Modifier.height(16.dp))
        Button(onClick = onCountChanged, enabled = count < 10) {
            Text("Click me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClickCounterPreview() {
    ComposeStatesTheme {
        ClickCounter()
    }
}

@Preview(showBackground = true)
@Composable
fun HoistedClickCounter() {
    ComposeStatesTheme {
        StatefulClickCounter()
    }
}