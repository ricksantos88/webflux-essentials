package com.wendel.spring.webflux.demo.essentials.domain.service

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.controller.model.TaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import com.wendel.spring.webflux.demo.essentials.domain.provider.TaskProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TaskService(
    private val taskProvider: TaskProvider
) {

    fun insert(taskDto: CreateTaskDTO): Mono<TaskDTO> =
        Mono.just(taskDto)
            .doOnNext { dto ->
                println("title: ${dto.title}")
                check(dto.title.isNotBlank()) { throw Exception("title cannot is blank") }
            }
            .map{ Task(it) }
            .flatMap(this::save)

    fun findTasks(pageNumber: Int, pageSize: Int): Mono<Page<TaskDTO>> =
        Mono.fromCallable { taskProvider.listTasksPaginated(PageRequest.of(pageNumber, pageSize)) }
            .map { it.map(::TaskDTO) }


//    fun list(): Flux<TaskDTO> =
//        Flux.fromIterable(taskProvider.listTasks())
//            .map { task -> TaskDTO(task) }
//            .map(::TaskDTO)

    private fun save(task: Task): Mono<TaskDTO> {
        return Mono.just(task).map { taskProvider.insertTask(task) }.map { TaskDTO(it) }
    }

    fun deleteById(id: String): Mono<Void> {
        return Mono.fromRunnable { taskProvider.deleteById(id) }
    }
}