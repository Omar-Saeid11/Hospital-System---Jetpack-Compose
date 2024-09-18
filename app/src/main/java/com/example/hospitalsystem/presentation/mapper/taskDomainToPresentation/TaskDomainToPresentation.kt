package com.example.hospitalsystem.presentation.mapper.taskDomainToPresentation

import com.example.hospitalsystem.domain.models.tasks.DomainData
import com.example.hospitalsystem.domain.models.tasks.DomainModelAllTasks
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainDataShowTask
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainModelShowTask
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainToDo
import com.example.hospitalsystem.domain.models.tasks.showTask.DomainUser
import com.example.hospitalsystem.presentation.models.tasks.PresentationData
import com.example.hospitalsystem.presentation.models.tasks.PresentationModelAllTasks
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationDataShowTask
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationModelShowTask
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationToDo
import com.example.hospitalsystem.presentation.models.tasks.showTask.PresentationUser

fun DomainData.toPresentation(): PresentationData {
    return PresentationData(
        createdAt, id, status, taskName
    )
}

fun DomainModelAllTasks.toPresentation(): PresentationModelAllTasks {
    return PresentationModelAllTasks(
        data?.map { it?.toPresentation() }, message, status
    )
}

fun DomainUser.toPresentation(): PresentationUser {
    return PresentationUser(
        address, birthday, email, firstName, gender, id, lastName, mobile, specialist
    )
}

fun DomainToDo.toPresentation(): PresentationToDo {
    return PresentationToDo(
        createdAt, id, taskId, title, updatedAt
    )
}

fun DomainDataShowTask.toPresentation(): PresentationDataShowTask {
    return PresentationDataShowTask(
        createdAt,
        description,
        id,
        note,
        status,
        taskName,
        toDo?.map { it?.toPresentation() },
        user?.toPresentation()
    )
}

fun DomainModelShowTask.toPresentation(): PresentationModelShowTask {
    return PresentationModelShowTask(
        data?.toPresentation(), message, status
    )
}