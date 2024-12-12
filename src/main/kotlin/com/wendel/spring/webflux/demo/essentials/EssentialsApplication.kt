package com.wendel.spring.webflux.demo.essentials

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EssentialsApplication

fun main(args: Array<String>) {
	runApplication<EssentialsApplication>(*args)
}
