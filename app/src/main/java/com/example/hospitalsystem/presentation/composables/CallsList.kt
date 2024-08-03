package com.example.hospitalsystem.presentation.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.models.calls.PresentationAllCalls

@Composable
fun CallsList(calls: PresentationAllCalls, onCallClick: (Int) -> Unit) {
    LazyColumn {
        items(calls.data) { call ->
            CallRow(call) { onCallClick(call.id) }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}