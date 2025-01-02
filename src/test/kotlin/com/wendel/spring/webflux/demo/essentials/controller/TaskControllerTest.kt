package com.wendel.spring.webflux.demo.essentials.controller

import com.wendel.spring.webflux.demo.essentials.controller.model.CreateTaskDTO
import com.wendel.spring.webflux.demo.essentials.controller.model.TaskDTO
import com.wendel.spring.webflux.demo.essentials.domain.model.TaskState
import com.wendel.spring.webflux.demo.essentials.domain.service.TaskService
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@SpringBootTest
class TaskControllerTest {

    @InjectMocks
    private lateinit var taskController: TaskController

    @Mock
    private lateinit var taskService: TaskService

    @Test
    fun `must return ok when save with successfully`() {
        val client = WebTestClient.bindToController(taskController).build()

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
}