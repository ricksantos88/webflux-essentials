package com.wendel.spring.webflux.demo.essentials.domain.provider

import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import com.wendel.spring.webflux.demo.essentials.domain.database.repository.TaskRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class TaskProvider(
    private val taskRepository: TaskRepository
) {

    fun listTasksPaginated(pageable: Pageable): Page<Task> = taskRepository.findAll(pageable)

    fun insertTask(task: Task): Task = taskRepository.save(task)

    fun deleteById(id: String) = taskRepository.deleteById(id)

}