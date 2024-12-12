package com.wendel.spring.webflux.demo.essentials.domain.shared

//import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
//import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState
//
//data class TaskBuilder(
//    var title: String = "",
//    var description: String = "",
//    var priority: Int = 0,
//    var state: TaskState = TaskState.INSERT
//) {
//
//    fun withTitle(title: String) = apply { this.title = title }
//    fun withDescription(description: String) = apply { this.description = description }
//    fun witPriority(priority: Int) = apply { this.priority = priority }
//    fun withState(state: TaskState) = apply { this.state = state }
//    fun build() = Task(this)
//
//    constructor(task: Task): this() {
//        this.title = task.title
//        this.description = task.description
//        this.priority = task.priority
//        this.state = task.state
//    }
//}