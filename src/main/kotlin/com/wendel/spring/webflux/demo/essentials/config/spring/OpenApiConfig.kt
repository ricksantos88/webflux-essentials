package com.wendel.spring.webflux.demo.essentials.config.spring

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Spring WebFlux Essentials")
                    .description("Este é um projeto de demonstração utilizando Kotlin, Spring Boot e WebFlux.")
                    .version("v1.0")
                    .contact(
                        Contact()
                            .name("Wendel Santos")
                            .email("henrique.santos@ggmail.com")
                            .url("https://github.com/ricksantos88")
                    )
            )
    }
}