package com.wendel.spring.webflux.demo.essentials.domain.service

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.controller.model.TaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState
import com.wendel.spring.webflux.demo.essentials.domain.provider.TaskProvider
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TaskService(
    private val taskProvider: TaskProvider
) {

    fun insert(taskDto: CreateTaskDTO): Mono<TaskDTO> =
        Mono.just(taskDto)
            .map{ Task(it) }
            .map(Task::insert)
            .flatMap(this::save)

    fun list(): Mono<List<TaskDTO>> {
        return Mono.just(taskProvider.listTasks())
            .map {
                taskList -> taskList.map {
                    TaskDTO(it)
                }
            }
    }

//    fun list(): Flux<TaskDTO> =
//        Flux.fromIterable(taskProvider.listTasks())
//            .map { task -> TaskDTO(task) }
//            .map(::TaskDTO)

    private fun save(task: Task): Mono<TaskDTO> {
        return Mono.just(task).map { taskProvider.insertTask(task) }.map { TaskDTO(it) }
    }
}