package org.venturus.br.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.venturus.br.testing.math.Calculator
import org.venturus.br.testing.ui.theme.TestingTheme

class MainActivity : ComponentActivity() {

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingTheme {
                MainScreen(calculator)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(calculator: Calculator) {
    var firstValue: Int? by remember { mutableStateOf(null) }
    var secondValue: Int? by remember { mutableStateOf(null) }
    var result: String by remember { mutableStateOf("") }

    fun areNumbersInserted(): Boolean = firstValue != null && secondValue != null

    val firstFieldDescription = stringResource(id = R.string.hint_first)
    val secondFieldDescription = stringResource(id = R.string.hint_second)
    val sumDescription = stringResource(id = R.string.button_sum)
    val subDescription = stringResource(id = R.string.button_sub)
    val divDescription = stringResource(id = R.string.button_divide)
    val resultDescription = stringResource(id = R.string.result_description)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .semantics { contentDescription = firstFieldDescription },
                value = firstValue?.toString() ?: "",
                onValueChange = {
                    firstValue = it.toIntOrNull()
                },
                label = { Text(stringResource(id = R.string.hint_first)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .semantics { contentDescription = secondFieldDescription },
                value = secondValue?.toString() ?: "",
                onValueChange = {
                    secondValue = it.toIntOrNull()
                },
                label = { Text(stringResource(id = R.string.hint_second)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (areNumbersInserted()) {
                        result = calculator.sum(firstValue!!, secondValue!!).toString()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .semantics { contentDescription = sumDescription }
            ) {
                Text(stringResource(id = R.string.button_sum))
            }
            Button(
                onClick = {
                    if (areNumbersInserted()) {
                        result = calculator.sub(firstValue!!, secondValue!!).toString()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .semantics { contentDescription = subDescription }
            ) {
                Text(stringResource(id = R.string.button_sub))
            }
            Button(
                onClick = {
                    if (areNumbersInserted()) {
                        result = calculator.divide(
                            firstValue!!.toDouble(),
                            secondValue!!.toDouble()
                        ).toString()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .semantics { contentDescription = divDescription }
            ) {
                Text(stringResource(id = R.string.button_divide))
            }
        }
        Text(
            text = result,
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .padding(top = 16.dp)
                .semantics { contentDescription = resultDescription }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestingTheme {
        MainScreen(Calculator())
    }
}