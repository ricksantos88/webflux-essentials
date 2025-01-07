package com.wendel.spring.webflux.demo.essentials.controller

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.controller.model.TaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState
import com.wendel.spring.webflux.demo.essentials.domain.service.TaskService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@Suppress("UNUSED_EXPRESSION")
@SpringBootTest
class TaskControllerTest {

    @InjectMocks
    private lateinit var taskController: TaskController

    @Mock
    private lateinit var taskService: TaskService

    lateinit var client: WebTestClient

    @BeforeEach
    fun setUp() {
        client = WebTestClient.bindToController(taskController).build()
    }

    @Test
    fun `must return ok when save with successfully`() {

        `when`(taskService.insert(CreateTaskDTO("title task", "description of task", 1)))
            .thenReturn(
                Mono.just(
                    TaskDTO("1", "title task", "description of task", 1, TaskState.INSERT)
                )
            )

        client.post()
            .uri("/task")
            .bodyValue(CreateTaskDTO("title task", "description of task", 1))
            .exchange()
            .expectStatus().isOk
            .expectBody(TaskDTO::class.java)
    }

    @Test
    fun `must return ok when get paginated successfully`() {
        val taskDTOs = listOf(TaskDTO("1", "title task", "description of task", 1, TaskState.INSERT))
        val pageRequest = PageRequest.of(0, 10)
        val taskPage: Page<TaskDTO> = PageImpl(taskDTOs, pageRequest, taskDTOs.size.toLong())

        `when`(taskService.findTasks(anyInt(), anyInt())).thenReturn(Mono.fromRunnable { taskPage })

        client.get()
            .uri("/task")
            .exchange()
            .expectStatus().isOk
            .expectBody(TaskDTO::class.java)
    }

    @Test
    fun `must return no content when delete with successfully`() {
        val taskId = "1"
        `when`(taskService.deleteById(taskId)).thenReturn(Mono.empty())

        client.delete()
            .uri("/task/$taskId")
            .exchange()
            .expectStatus().isNoContent
    }
}