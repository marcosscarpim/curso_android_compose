package org.venturus.br.composestates.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.venturus.br.composestates.data.Employee

@Composable
fun EmployeeListItem(
    modifier: Modifier = Modifier,
    employee: Employee
) {
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                EmployeeProfileImage(
                    drawableResource = employee.avatar,
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = employee.name)
                    Text(text = employee.currentProject)
                }
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.clip(CircleShape)
                ) {
                    Icon(imageVector = Icons.Sharp.Star, contentDescription = null)
                }
            }
            Text(
                text = employee.currentRole,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            )
            Text(
                text = employee.competences.joinToString(", "),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}