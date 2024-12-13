package com.wendel.spring.webflux.demo.essentials.controller

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.controller.model.TaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.service.TaskService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/task")
class TaskController(
    private val taskService: TaskService
) {

    @GetMapping
    fun findAllTasks(
        @RequestParam(value = "pageNumber", defaultValue = "0") pageNumber: Int,
        @RequestParam(value = "pageSize", defaultValue = "10") pageSize: Int
    ): Mono<Page<TaskDTO>> = taskService.findTasks(pageNumber, pageSize)

    @PostMapping
    fun createTask(@RequestBody taskDTO: CreateTaskDTO) = taskService.insert(taskDTO)

}