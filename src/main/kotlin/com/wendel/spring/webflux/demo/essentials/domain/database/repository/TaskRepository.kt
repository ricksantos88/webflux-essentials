package com.wendel.spring.webflux.demo.essentials.domain.database.repository

import com.wendel.spring.webflux.demo.essentials.domain.database.entity.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository: MongoRepository<Task, String> {
}