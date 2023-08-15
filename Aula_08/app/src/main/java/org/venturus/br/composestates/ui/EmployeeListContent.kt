package org.venturus.br.composestates.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.venturus.br.composestates.data.Employee
import org.venturus.br.composestates.ui.components.EmployeeListItem

@Composable
fun EmployeeListScreen(
    modifier: Modifier = Modifier,
    employeeHomeUIState: EmployeeHomeUIState
) {

    // avoid recomposition if current state changes but list is not impacted
    val employeeLazyListState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        EmployeeListContent(
            employees = employeeHomeUIState.employees,
            employeeLazyListState = employeeLazyListState,
            modifier = Modifier.fillMaxSize(),
        )

        LargeFloatingActionButton(
            onClick = { /*Click Implementation*/ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun EmployeeListContent(
    employees: List<Employee>,
    employeeLazyListState: LazyListState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier, state = employeeLazyListState) {
        items(items = employees, key = { it.id }) { employee ->
            EmployeeListItem(employee = employee)
        }
    }
}
