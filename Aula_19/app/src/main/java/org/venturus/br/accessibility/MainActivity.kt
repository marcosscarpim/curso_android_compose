package org.venturus.br.accessibility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.venturus.br.accessibility.ui.theme.AccessibilityTheme
import org.venturus.br.accessibility.ui.theme.ButtonColor
import org.venturus.br.accessibility.ui.theme.MyGray
import org.venturus.br.accessibility.ui.theme.navBarColor

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AccessibilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { MainTopBar() }
                    ){ innerPadding ->
                        MainScreen(Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.app_name))
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = navBarColor,
            titleContentColor = Color.White
        )
    )
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.monstera),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.product_monstera_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.product_monstera_description),
            style = MaterialTheme.typography.bodySmall,
            color = MyGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_star),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
            )

            Button(
                onClick = { /* Handle button click here */ },
                modifier = Modifier
                    .height(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
            ) {
                Text(
                    text = stringResource(id = R.string.product_learn_more),
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.product_all_rights_reserved),
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AccessibilityTheme {
        MainScreen()
    }
}