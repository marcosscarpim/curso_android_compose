package org.venturus.br.composelayoutbasics

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ColumnLayout() {
    Column {
        ElementOrange()
        ElementBlue()
        ElementGreen()
    }
}

@Composable
fun RowLayout() {
    Row {
        ElementOrange()
        ElementBlue()
        ElementGreen()
    }
}

@Composable
fun BoxLayout() {
    Box {
        ElementOrange()
        ElementBlue()
        ElementGreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnBasicPreview() {
    ColumnLayout()
}

@Preview(showBackground = true)
@Composable
fun RowBasicPreview() {
    RowLayout()
}

@Preview(showBackground = true)
@Composable
fun BoxBasicPreview() {
    BoxLayout()
}