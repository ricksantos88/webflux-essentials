package com.wendel.spring.webflux.demo.essentials.controller.model

import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task

data class CreateTaskDTO(
    val title: String,
    val description: String,
    val priority: Int
) {
    fun convertToTaskEntity() = Task(
        title = title,
        description = description,
        priority = priority
    )
}
