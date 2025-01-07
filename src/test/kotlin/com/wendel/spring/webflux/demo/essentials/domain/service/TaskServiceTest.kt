package com.wendel.spring.webflux.demo.essentials.domain.service

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.controller.model.TaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState
import com.wendel.spring.webflux.demo.essentials.domain.provider.TaskProvider
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import reactor.test.StepVerifier

@SpringBootTest
class TaskServiceTest {

    private val taskProvider = mock(TaskProvider::class.java)
    private val taskService = TaskService(taskProvider)

    @Test
    fun `test insert`() {
        val createTaskDTO = CreateTaskDTO("title task", "description of task", 1)
        val task = Task(createTaskDTO)
        val taskDTO = TaskDTO("1", "title task", "description of task", 1, TaskState.INSERT)

        `when`(taskProvider.insertTask(task)).thenReturn(task)

        val result = taskService.insert(createTaskDTO)

        StepVerifier.create(result)
            .assertNext {
                assertEquals(taskDTO.title, it.title)
                assertEquals(taskDTO.description, it.description)
                assertEquals(taskDTO.priority, it.priority)
                assertEquals(taskDTO.state, it.state)
            }
            .verifyComplete()
    }

    @Test
    fun `test findTasks`() {
        val tasks = listOf(Task("1", "title task", "description of task", 1, TaskState.INSERT))
        val tasksDto = listOf(TaskDTO("1", "title task", "description of task", 1, TaskState.INSERT))
        val pageRequest = PageRequest.of(0, 10)
        val taskPage = PageImpl(tasks, pageRequest, tasks.size.toLong())

        `when`(taskProvider.listTasksPaginated(pageRequest)).thenReturn(taskPage)

        val result = taskService.findTasks(0, 10)

        StepVerifier.create(result)
            .assertNext {
                assertEquals(1, it.totalElements)
                assertEquals(tasksDto[0], it.content[0])
            }
            .verifyComplete()
    }

    @Test
    fun `test deleteById`() {
        val taskId = "1"

        doNothing().`when`(taskProvider).deleteById(taskId)

        val result = taskService.deleteById(taskId)

        StepVerifier.create(result)
            .verifyComplete()

        verify(taskProvider, times(1)).deleteById(taskId)
    }

}