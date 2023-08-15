package org.venturus.br.composestates.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.venturus.br.composestates.data.Employee
import java.text.NumberFormat
import java.util.Currency

val salaryFormat = NumberFormat.getCurrencyInstance().apply {
    maximumFractionDigits = 0
    currency = Currency.getInstance("EUR")
}

@Composable
fun EmployeeListItem(
    modifier: Modifier = Modifier,
    employee: Employee
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var favorited by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { expanded = !expanded }
            .animateContentSize()
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
                    val nameWeight = if (expanded) FontWeight.Bold else FontWeight.Normal

                    Text(text = employee.name, fontWeight = nameWeight)
                    Text(text = employee.currentProject)
                }
                IconButton(
                    onClick = { favorited = !favorited },
                    modifier = Modifier.clip(CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Star,
                        tint = if (favorited) Color.Yellow else LocalContentColor.current,
                        contentDescription = null
                    )
                }
            }
            Text(
                text = employee.currentRole,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
            )
            Text(
                text = employee.competences.joinToString(", "),
                modifier = Modifier.padding(bottom = 8.dp),
                maxLines = if (expanded) 2 else 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (expanded) {
                Text(text = salaryFormat.format(employee.salary))
            }
        }
    }
}