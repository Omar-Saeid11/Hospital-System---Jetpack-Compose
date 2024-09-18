package com.example.hospitalsystem.presentation.screens.common.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationModelShowTask

@Composable
fun TaskDetailsContent(
    task: PresentationModelShowTask,
    taskId: Int,
    isExecutionEnabled: Boolean,
    onExecuteClick: (Int, String) -> Unit
) {
    val checkedStates = remember { mutableStateListOf<Boolean>() }

    task.data?.toDo?.let { toDoList ->
        if (checkedStates.isEmpty()) {
            checkedStates.addAll(List(toDoList.size) { false })
        }
    }
    var noteText by remember { mutableStateOf("") }

    val isButtonEnabled = task.data?.status == "pending" && isExecutionEnabled

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        task.data?.taskName?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray, shape = RoundedCornerShape(16.dp))
                    .padding(12.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberVectorPainter(Icons.Default.Person),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                task.data?.user?.firstName?.let { Text(text = it, fontWeight = FontWeight.Bold) }
                task.data?.user?.specialist?.let { Text(text = it, color = Color.Green) }
            }
            Spacer(modifier = Modifier.weight(1f))
            task.data?.createdAt?.let { Text(text = it) }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Details note: ${task.data?.note}")
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "To do", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        task.data?.toDo?.forEachIndexed { index, toDoItem ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { isChecked ->
                        checkedStates[index] = isChecked
                    },
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .padding(end = 8.dp),
                    colors = androidx.compose.material3.CheckboxDefaults.colors(Color(0xFF22C7B8))
                )
                Text(text = toDoItem?.title ?: "")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Add Note") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onExecuteClick(taskId, noteText) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF22C7B8)),
            enabled = isButtonEnabled
        ) {
            Text(text = "Execution", color = Color.White)
        }
    }
}
