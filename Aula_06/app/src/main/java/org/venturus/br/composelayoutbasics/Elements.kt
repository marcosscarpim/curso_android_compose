package org.venturus.br.composelayoutbasics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ElementOrange() {
    Spacer(modifier = Modifier
        .size(200.dp)
        .background(Color.Red))
}

@Composable
fun ElementBlue() {
    Spacer(modifier = Modifier
        .size(100.dp)
        .background(Color.Blue))
}

@Composable
fun ElementGreen() {
    Spacer(modifier = Modifier
        .width(150.dp)
        .height(30.dp)
        .background(Color.Green))
}

@Preview(showBackground = true)
@Composable
fun ElementOrangePreview() {
    ElementOrange()
}


@Preview(showBackground = true)
@Composable
fun ElementBluePreview() {
    ElementBlue()
}


@Preview(showBackground = true)
@Composable
fun ElementGreenPreview() {
    ElementGreen()
}