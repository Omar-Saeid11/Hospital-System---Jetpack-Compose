package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType

@Composable
fun EmployeeList(employees: List<PresentationUserType>, onClickItem: (Int) -> Unit) {
    LazyColumn {
        items(employees) { employee ->
            EmployeeItem(employee) {
                onClickItem(employee.id)
            }
        }
    }
}

