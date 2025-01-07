package com.wendel.spring.webflux.demo.essentials.domain.database.entity

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState
import org.springframework.data.annotation.Id

//import com.wendel.spring.webflux.demo.essentials.domain.shared.TaskBuilder

data class Task (

    @Id
    val id: String? = null,
    val title: String,
    val description: String,
    val priority: Int,
    var state: TaskState? = null
) {
    constructor(taskDTO: CreateTaskDTO): this(
        title = taskDTO.title,
        description = taskDTO.description,
        priority = taskDTO.priority,
        state = TaskState.INSERT
    )

//    companion object {
//        fun builder() = TaskBuilder()
//        fun builderFrom(task: Task) = TaskBuilder(task)
//    }
//
//    constructor(taskBuilder: TaskBuilder): this(
//        taskBuilder.title,
//        taskBuilder.description,
//        taskBuilder.priority,
//        taskBuilder.state
//    )
}

