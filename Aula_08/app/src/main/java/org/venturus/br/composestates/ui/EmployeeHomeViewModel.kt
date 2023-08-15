package org.venturus.br.composestates.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.venturus.br.composestates.data.Employee
import org.venturus.br.composestates.data.EmployeeDataProvider

class EmployeeHomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(EmployeeHomeUIState(loading = true))
    val uiState: StateFlow<EmployeeHomeUIState> = _uiState

    init {
        initEmployeeList()
    }

    private fun initEmployeeList() {
        val employees = EmployeeDataProvider.allEmployees
        _uiState.value = EmployeeHomeUIState(
            employees = employees,
            selectedEmployee = employees.first()
        )
    }
}

data class EmployeeHomeUIState(
    val employees: List<Employee> = emptyList(),
    val selectedEmployee: Employee? = null,
    val loading: Boolean = false,
    val error: String? = null
)