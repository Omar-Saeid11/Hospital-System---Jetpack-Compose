package com.example.hospitalsystem.presentation.screens.common.cases.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import com.example.hospitalsystem.presentation.models.hr.userType.PresentationUserType

@Composable
fun EmployeeSelectionList(
    nurses: List<PresentationUserType>,
    selectedNurse: PresentationUserType?,
    onNurseSelected: (PresentationUserType) -> Unit
) {
    LazyColumn {
        items(nurses) { nurse ->
            EmployeeSelectionListItem(
                nurse = nurse,
                isSelected = nurse == selectedNurse,
                onNurseSelected = onNurseSelected
            )
            Divider()
        }
    }
}