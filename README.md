# Spring WebFlux Demo

Este é um projeto de demonstração utilizando Kotlin, Spring Boot e WebFlux. O projeto inclui um serviço de gerenciamento de tarefas com operações de criação e listagem de tarefas.

## Tecnologias Utilizadas

- Kotlin
- Spring Boot
- Spring WebFlux
- Gradle

## Estrutura do Projeto

- `src/main/kotlin/com/wendel/spring/webflux/demo/essentials/controller`: Contém os controladores REST.
- `src/main/kotlin/com/wendel/spring/webflux/demo/essentials/controller/model`: Contém os DTOs utilizados pelos controladores.
- `src/main/kotlin/com/wendel/spring/webflux/demo/essentials/domain/database/entity`: Contém as entidades do banco de dados.
- `src/main/kotlin/com/wendel/spring/webflux/demo/essentials/domain/service`: Contém os serviços que implementam a lógica de negócios.
- `src/main/kotlin/com/wendel/spring/webflux/demo/essentials/domain/provider`: Contém os provedores de dados.

## Endpoints

### Listar Tarefas

- **URL**: `/task`
- **Método**: `GET`
- **Resposta**: Lista de tarefas.

### Criar Tarefa

- **URL**: `/task`
- **Método**: `POST`
- **Corpo da Requisição**:
  ```json
  {
    "title": "Título da Tarefa",
    "description": "Descrição da Tarefa",
    "priority": 1
  }