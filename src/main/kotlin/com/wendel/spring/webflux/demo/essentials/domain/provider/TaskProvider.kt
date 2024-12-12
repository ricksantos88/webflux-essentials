package com.wendel.spring.webflux.demo.essentials.domain.provider

import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import com.wendel.spring.webflux.demo.essentials.domain.database.repository.TaskRepository
import org.springframework.stereotype.Component

@Component
class TaskProvider(
    private val taskRepository: TaskRepository
) {

    fun listTasks(): List<Task> = taskRepository.findAll()

    fun insertTask(task: Task): Task = taskRepository.save(task)
}