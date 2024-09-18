package com.example.hospitalsystem.data.mapper.tasksDataToDomain

import com.example.hospitalsystem.data.models.tasks.ModelAllTasks
import com.example.hospitalsystem.data.models.tasks.showTask.Data
import com.example.hospitalsystem.data.models.tasks.showTask.ModelShowTask
import com.example.hospitalsystem.data.models.tasks.showTask.ToDo
import com.example.hospitalsystem.data.models.tasks.showTask.User
import com.example.hospitalsystem.domain.models.tasks.DomainData
import com.example.hospitalsystem.domain.models.tasks.DomainModelAllTasks
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainDataShowTask
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainModelShowTask
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainToDo
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainUser

fun User.toDomain(): DomainUser {
    return DomainUser(
        address, birthday, email, firstName, gender, id, lastName, mobile, specialist
    )
}

fun ToDo.toDomain(): DomainToDo {
    return DomainToDo(
        createdAt, id, taskId, title, updatedAt
    )
}

fun Data.toDomain(): DomainDataShowTask {
    return DomainDataShowTask(
        createdAt,
        description,
        id,
        note,
        status,
        taskName,
        this.toDo?.map { it?.toDomain() },
        user?.toDomain()
    )
}

fun ModelShowTask.toDomain(): DomainModelShowTask {
    return DomainModelShowTask(
        data?.toDomain(), message, status
    )
}

fun com.example.hospitalsystem.data.models.tasks.Data.toDomain(): DomainData {
    return DomainData(
        createdAt, id, status, taskName
    )
}

fun ModelAllTasks.toDomain(): DomainModelAllTasks {
    return DomainModelAllTasks(
        data?.map { it?.toDomain() }, message, status
    )
}