package com.wendel.spring.webflux.demo.essentials.controller.model

import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState

data class TaskDTO(
    val id: String,
    val title: String,
    val description: String,
    val priority: Int,
    var state: TaskState?
) {
    constructor(task: Task) : this(
        task.id.let { it ?: "" },
        task.title,
        task.description,
        task.priority,
        task.state
    )
}
