package com.wendel.spring.webflux.demo.essentials.controller

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.service.TaskService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/task")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    fun getTask() = taskService.list()

    @PostMapping
    fun createTask(@RequestBody taskDTO: CreateTaskDTO) = taskService.insert(taskDTO)

}